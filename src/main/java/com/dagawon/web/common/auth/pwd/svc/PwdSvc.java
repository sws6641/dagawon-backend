package com.dagawon.web.common.auth.pwd.svc;


import com.dagawon.web.common.auth.pwd.vo.PwdVo;
import com.dagawon.web.common.commSvc.MailSvc;
import com.dagawon.web.common.commSvc.TransactionHandler;
import com.dagawon.web.common.dto.TbAuthCodeDto;
import com.dagawon.web.common.dto.TbMailInfoDto;
import com.dagawon.web.common.dto.TbMembDto;
import com.dagawon.web.common.entity.TbAuthCode;
import com.dagawon.web.common.entity.TbMailInfo;
import com.dagawon.web.common.entity.TbMemb;
import com.dagawon.web.common.mapper.TbAuthCodeMapper;
import com.dagawon.web.common.mapper.TbMailInfoMapper;
import com.dagawon.web.common.mapper.TbMembMapper;
import com.dagawon.web.common.repo.TbAuthCodeRepository;
import com.dagawon.web.common.repo.TbMailInfoRepository;
import com.dagawon.web.common.repo.TbMembRepository;
import com.dagawon.web.common.util.CodeUtil;
import com.dagawon.web.config.exception.BadRequestException;
import com.dagawon.web.config.exception.DefaultException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Optional;


/**
 * 회원 패스워드
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PwdSvc {

    private final MailSvc mailSvc;
    private final TbMembRepository tbMembRepository;
    private final TbAuthCodeRepository tbAuthCodeRepository;
    private final TbMailInfoRepository tbMailInfoRepository;
    private final TbMembMapper tbMembMapper;
    private final TbAuthCodeMapper tbAuthCodeMapper;
    private final TbMailInfoMapper tbMailInfoMapper;

    @Transactional
    public void sendAuthCodeMail(PwdVo.SendAuthCodeMailReq req) throws Exception {

        Optional<TbMemb> tbMemb = tbMembRepository.findByMembEmail(req.getMembEmail());

        if (tbMemb.isEmpty()) {
            throw new BadRequestException("회원정보가 존재하지 않습니다.");
        }

        TbMembDto tbMembDto = tbMembMapper.toDto(tbMemb.get());

        if (!StringUtils.hasText(tbMembDto.getMembExtEmail())) {
            throw new BadRequestException("회원정보에 이메일이 존재하지 않습니다.");
        }

        Optional<TbMailInfo> mailInfo = tbMailInfoRepository.findTop1ByAcntCdAndUseYnOrderByCrtDtmDesc("01" , "Y");

        if (mailInfo.isEmpty()) {
            throw new DefaultException("메일 발송 설정(From Email)을 찾을 수 없습니다.");
        }

        //다가원 시스템 메일 정보
        TbMailInfoDto mailInfoDto = tbMailInfoMapper.toDto(mailInfo.get());

        // 인증번호 발급
        String authCode  = CodeUtil.generateAuthCode(6);

        String subject = "[Dagawon] 비밀번호 재설정 인증번호 안내";

        String html = """
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>다가원 그룹웨어 비밀번호 재설정</title>
</head>
<body style="margin:0; padding:40px 0; background:#ffffff; font-family:-apple-system, BlinkMacSystemFont, 'Segoe UI', Helvetica, Arial, sans-serif;">

<table width="100%%" cellpadding="0" cellspacing="0">
    <tr>
        <td align="center">
            <table width="600" cellpadding="0" cellspacing="0" style="padding: 0 24px;">
                
                <!-- Title -->
                <tr>
                    <td style="font-size:20px; font-weight:600; color:#1a7f37; padding-bottom:24px;">
                        비밀번호 재설정 인증 코드
                    </td>
                </tr>

                <!-- Greeting -->
                <tr>
                    <td style="font-size:15px; color:#222; line-height:1.6; padding-bottom:16px;">
                        안녕하세요.<br/>
                        다가원 그룹웨어입니다.
                    </td>
                </tr>

                <!-- Email Info -->
                <tr>
                    <td style="font-size:15px; color:#444; line-height:1.6; padding-bottom:24px;">
                        %s 계정의 비밀번호 재설정을 위해<br/>
                        인증 코드를 발송 드립니다.
                    </td>
                </tr>

                <!-- Code Box -->
                <tr>
                    <td>
                        <table width="100%%" cellpadding="0" cellspacing="0" 
                               style="background:#f1f3f5; border-radius:10px; padding:24px; text-align:center;">
                            <tr>
                                <td style="font-size:14px; color:#555; padding-bottom:8px;">
                                    인증 코드
                                </td>
                            </tr>
                            <tr>
                                <td style="font-size:24px; font-weight:700; color:#000; letter-spacing:2px;">
                                    %s
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>

                <!-- Footer -->
                <tr>
                    <td style="font-size:14px; color:#555; line-height:1.6; padding-top:24px;">
                        감사합니다.<br/>
                        다가원 그룹웨어 드림
                    </td>
                </tr>

            </table>
        </td>
    </tr>
</table>

</body>
</html>
""".formatted(tbMembDto.getMembEmail(), authCode);

    // 메일 발송
   Long mailLogId = mailSvc.sendHtmlMail(tbMembDto.getMembExtEmail(),mailInfoDto.getEmail(), subject, html);

   //인증번호 테이블 저장
      TbAuthCodeDto tbAuthCodeDto = TbAuthCodeDto.builder()
                .membEmail(tbMembDto.getMembExtEmail()) // 회원이메일
                .authTypeCd("10") // 인증타입
                .authCode(authCode) // 인증코드
                .expireDtm(LocalDateTime.now().plusMinutes(30)) // 유효기간 30분 뒤
                .mailLogId(mailLogId)
                .usedYn("N") // 사용여부
                .build();

      tbAuthCodeRepository.save(tbAuthCodeMapper.toEntity(tbAuthCodeDto));

    }

    @Transactional
    public String verifyAuthCodeMail(PwdVo.VerifyAuthCodeMailReq req){

        Optional<TbAuthCode> tbAuthCodeOpt = tbAuthCodeRepository.findTop1ByMembEmailOrderByExpireDtmDesc(req.getMembEmail());

        if (tbAuthCodeOpt.isEmpty()) return "01";

        TbAuthCodeDto dto = tbAuthCodeMapper.toDto(tbAuthCodeOpt.get());

        boolean isValidTime = dto.getExpireDtm().plusMinutes(1).isAfter(LocalDateTime.now());

        if (!isValidTime) return "02";

        boolean isCorrect = dto.getAuthCode().equals(req.getAuthCode());
        if (!isCorrect) return "03";

        dto.setUsedDtm(LocalDateTime.now());
        dto.setUsedYn("Y");
        tbAuthCodeRepository.save(tbAuthCodeMapper.toEntity(dto));

        return "00";
    }

}
