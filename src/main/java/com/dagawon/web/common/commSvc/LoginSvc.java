package com.dagawon.web.common.commSvc;

import com.dagawon.web.common.commSvc.vo.LoginVo;
import com.dagawon.web.common.dto.TbMembDto;
import com.dagawon.web.common.entity.TbMemb;
import com.dagawon.web.common.mapper.TbMembMapper;
import com.dagawon.web.common.repo.*;
import com.dagawon.web.common.userAuth.UserAuthSvc;
import com.dagawon.web.common.util.CustomeModelMapper;
import com.dagawon.web.config.security.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Optional;
import java.util.regex.Pattern;

/**
 * 공통 - 로그인
 *
 * @author 정가은
 * @version 1.0
 * @since 2025.03.12
 */
@Slf4j
@Service
@RequiredArgsConstructor
@RequestScope
public class LoginSvc {
    private final JwtUtil jwtUtil;
    private final UserAuthSvc userAuthSvc;
    private final TbDeptRepository tbDeptRepository;
    private final TbMembRepository tbMembRepository;
    private final TbCompanyRepository tbCompanyRepository;
    private final TbMembDeptRepository tbMembDeptRepository;
    private final TbPositionRepository tbPositionRepository;

    private final CustomeModelMapper customeModelMapper;
    private final TbMembMapper tbMembMapper;


    /**
     * 사용자 로그인
     *
     * @param loginReqVo : 사용자 입력 정보
     * @return LoginRes : 사용자 리스트, 토큰
     * @throws Exception
     */

    @Transactional
    public LoginVo.LoginRes getLogin(LoginVo.LoginReq loginReqVo, HttpServletResponse response) throws Exception {
        String loginId = loginReqVo.getId();
        String membGbCd = loginReqVo.getMembGbCd();
        LoginVo.LoginRes result = new LoginVo.LoginRes();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        //------------------------------------------------------------------
        // 공통코드 조회
        //------------------------------------------------------------------
        // MEMB_GB_CD: 금융기관(00), 법무 대리인(10), 관리자(20) / MEMB_STAT_CD : 승인(10), 잠김(20), 탈퇴(30) 등
//        List<String> loginGbCd = List.of("MEMB_GB_CD", "MEMB_STAT_CD");
//        Map<String, Map<String, String>> codeMap = commSvc.searchCommCodeMultiList(loginGbCd);

        //------------------------------------------------------------------
        // 회원 조회
        //------------------------------------------------------------------
        Optional<TbMemb> tbMemb = tbMembRepository.findByMembId(loginId);
        if (tbMemb.isPresent()) {
            TbMembDto tbMembDto = tbMembMapper.toDto(tbMemb.get());
            Long membNo = tbMembDto.getMembNo();
            String membNm = tbMembDto.getMembNm();
//            String bizNo = tbMembDto.getBizNo();

            //------------------------------------------------------------------
            // 패스워드 일치 여부 판단
            //------------------------------------------------------------------
            if (!encoder.matches(loginReqVo.getPwd(), tbMembDto.getMembPwd())) {
                result.setResCd("01");
                return result;
            }

            //------------------------------------------------------------------
            // 토큰 가져오기
            //------------------------------------------------------------------
            // TODO: 20251111 - 사업자번호 세팅 수정 필요
            LoginVo.TokenReq tokenReq = LoginVo.TokenReq.builder()
                    .loginId(loginId)
                    .membNo(membNo)
                    .membNm(membNm)
//                    .bizNo(bizNo)
                    .build();

            String accessToken = jwtUtil.getUserAccessToken(tokenReq);
            String refreshToken = jwtUtil.getUserRefreshToken(tokenReq);

            result = customeModelMapper.mapping(tbMembDto, LoginVo.LoginRes.class);
//            result.setMembGbNm(codeMap.get("MEMB_GB_CD").getOrDefault(membGbCd, "미확인"));
//            result.setStatNm(codeMap.get("MEMB_STAT_CD").getOrDefault(custDto.getStatCd(), "미확인"));
            result.setAccessToken(accessToken);
            result.setLognYn(true);
            result.setResCd("00");

            addRefreshTokenToCookie(response, refreshToken);

        } else {
            result.setResCd("99");
        }

        return result;
    }

    /**
     * 사용자 로그아웃
     */
    public void getLogout(HttpServletResponse response) throws Exception {
        SecurityContextHolder.clearContext();
        delRefreshTokenFromCookie(response);
    }

    /**
     * 프로필 이미지 조회
     *
     * @return ImageRes 프로필 이미지 정보
     * @throws Exception
     */
//    public ImageVo.ImageRes getOfficeProfile() throws Exception {
//        String bizNo = userAuthSvc.getBizNo();
//
//        var office = tbOfficeMasterRepository.findByBizNo(bizNo);
//
//        if (office.isPresent()) {
//            ImageVo.ImageInfoReq imageReq = ImageVo.ImageInfoReq.builder()
//                    .seq(bizNo)
//                    .attcFilCd("06")
//                    .build();
//
//            ImageVo.ImageRes imageRes = fileSvc.getImages(imageReq);
//            return imageRes;
//        }
//        return null;
//    }

    /**
     * 사용자 비밀번호 변경
     *
     * @param pwdChgReq : id, 기존pw, 새로운pw, 재 확인용pw
     * @return (String) : 사용자 명
     * @throws Exception
     */
//    @Transactional
//    public String edtPwd(LoginVo.PwdChgReq pwdChgReq) throws Exception {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//
//        String membNo = pwdChgReq.getMembNo();
//        String usedPwd = pwdChgReq.getPwd();
//        String newPwd = pwdChgReq.getNewPwd();
//        String reNewPwd = pwdChgReq.getReNewPwd();
//
//        Optional<TbCustMaster> tbCustMaster = tbCustMasterRepository.findByMembNo(membNo);
//        if (tbCustMaster.isPresent()) {
//            TbCustMasterDto custDto = TbCustMasterMapper.INSTANCE.toDto(tbCustMaster.get());
//
//            // 01 : 현재 비밀번호와 일치하지 않습니다.
//            if (!encoder.matches(usedPwd, custDto.getMembPwd())) {
//                return "01";
//            }
//
//            // 02 : 8~20 자 영문, 숫자를 모두 포함한 비밀번호를 입력해 주세요.
//            if (!isValidPassword(newPwd)) {
//                return "02";
//            }
//
//            // 03 : 비밀번호가 일치하지 않습니다.
//            if (!newPwd.equals(reNewPwd)) {
//                return "03";
//            }
//
//            // 04 : 이전과 동일한 비밀번호 입니다.
//            if (usedPwd.equals(newPwd)) {
//                return "04";
//            }
//
//            // 05 : 비밀번호를 입력해주세요.
//            if (StringUtils.isBlank(membNo) || StringUtils.isBlank(pwdChgReq.getPwd()) || StringUtils.isBlank(newPwd) || StringUtils.isBlank(reNewPwd)) {
//                return "05";
//            }
//            // 비밀번호 저장
//            custDto.setMembPwd(encoder.encode(newPwd));
//
//            tbCustMasterRepository.save(TbCustMasterMapper.INSTANCE.toEntity(custDto));
//
//            return "00";
//        } else {
//            return "99";
//        }
//
//    }

    /**
     * 비밀번호 정책 (비밀번호가 8자 이상 ~ 20자 이하, 숫자/영문자 반드시 포함) 확인
     *
     * @param password
     * @return 비밀번호 정책이 지켜졌을 경우 true
     */
    public static boolean isValidPassword(String password) {
        return Pattern.matches("^(?=.*[A-Za-z])(?=.*\\d)(?!.*[ㄱ-ㅎㅏ-ㅣ가-힣])[A-Za-z\\d!@#$%^&*()_+=\\-{}\\[\\]|:;\"'<>,.?/~`]{8,20}$", password);
    }

    /**
     * 로그인시
     * refreshToken을 HttpOnly 쿠키로 응답 헤더에 설정
     */
    public void addRefreshTokenToCookie(HttpServletResponse response, String refreshToken) {
        ResponseCookie refreshTokenCookie = ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)
                .secure(true)
                .sameSite("Strict")
                .path("/")
                .build();

        response.addHeader("Set-Cookie", refreshTokenCookie.toString());
    }

    /**
     * 로그아웃시
     * 사용자의 refreshToken 즉시 만료
     */
    public void delRefreshTokenFromCookie(HttpServletResponse response) {
        ResponseCookie deleteCookie = ResponseCookie.from("refreshToken", "")
                .httpOnly(true)
                .secure(true)
                .sameSite("Strict")
                .path("/")
                .maxAge(0) // 즉시 만료
                .build();

        response.addHeader("Set-Cookie", deleteCookie.toString());
    }

}
