package com.dagawon.web.common.auth.pwd.svc;


import com.dagawon.web.common.auth.pwd.vo.PwdVo;
import com.dagawon.web.common.commSvc.MailSvc;
import com.dagawon.web.common.dto.TbMembDto;
import com.dagawon.web.common.entity.TbMemb;
import com.dagawon.web.common.mapper.TbMembMapper;
import com.dagawon.web.common.repo.TbMembRepository;
import com.dagawon.web.common.util.NumberUtil;
import com.dagawon.web.config.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
    private final TbMembMapper tbMembMapper;

    @Transactional
    public void sendAuthCodeMail(PwdVo.SendAuthCodeMailReq req) throws Exception {

        Optional<TbMemb> tbMemb = tbMembRepository.findByMembId(req.getMembId());

        if (tbMemb.isEmpty()) {
            throw new BadRequestException("회원정보가 존재하지 않습니다.");
        }

        TbMembDto tbMembDto = tbMembMapper.toDto(tbMemb.get());

        if (!StringUtils.hasText(tbMembDto.getMembEmail())) {
            throw new BadRequestException("회원정보에 이메일이 존재하지 않습니다.");
        }

        // 인증번호 발급
        String authNum  = NumberUtil.getRandomNumber(6);

        // TODO: 인증번호 저장 로직 (DB/Redis 등)

        String subject = "[Dagawon] 비밀번호 재설정 인증번호 안내";

        String html = """
            <!DOCTYPE html>
            <html lang="ko">
            <head>
                <meta charset="UTF-8">
                <title>Dagawon 비밀번호 재설정</title>
            </head>
            <body style="margin:0;padding:0;background-color:#f5f5f5;font-family:-apple-system,BlinkMacSystemFont,'Segoe UI',sans-serif;">
            <table width="100%" cellpadding="0" cellspacing="0" style="padding:24px 0;">
              <tr>
                <td align="center">
                  <table width="480" cellpadding="0" cellspacing="0" 
                         style="background-color:#ffffff;border-radius:8px;padding:24px;box-shadow:0 2px 8px rgba(0,0,0,0.06);">
                    <tr>
                      <td style="font-size:20px;font-weight:600;color:#222222;padding-bottom:16px;">
                        Dagawon 비밀번호 재설정 인증번호
                      </td>
                    </tr>
                    <tr>
                      <td style="font-size:14px;color:#444444;line-height:1.6;padding-bottom:16px;">
                        안녕하세요, Dagawon입니다.<br/>
                        비밀번호 재설정을 위해 요청하신 인증번호를 안내드립니다.<br/>
                        아래 인증번호를 화면에 입력하여 본인 확인을 완료해주세요.
                      </td>
                    </tr>
                    <tr>
                      <td align="center" 
                          style="padding:16px 0 20px 0;">
                        <div style="display:inline-block;padding:12px 24px;border-radius:6px;
                                    background-color:#2563eb;color:#ffffff;
                                    font-size:24px;font-weight:700;letter-spacing:4px;">
                          %s
                        </div>
                      </td>
                    </tr>
                    <tr>
                      <td style="font-size:12px;color:#777777;line-height:1.6;padding-top:8px;">
                        본인이 요청하지 않은 경우, 개인정보 보호를 위해<br/>
                        Dagawon 고객센터로 즉시 문의해 주세요.
                      </td>
                    </tr>
                    <tr>
                      <td style="font-size:12px;color:#aaaaaa;padding-top:24px;border-top:1px solid #eeeeee;">
                        감사합니다.<br/>
                        Dagawon 팀 드림
                      </td>
                    </tr>
                  </table>
                </td>
              </tr>
            </table>
            </body>
            </html>
            """.formatted(authNum);

        mailSvc.sendHtmlMail(tbMembDto.getMembEmail(), subject, html);
    }

}
