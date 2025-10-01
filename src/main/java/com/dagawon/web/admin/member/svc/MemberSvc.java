package com.dagawon.web.admin.member.svc;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 인증 서비스
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MemberSvc {




//    /**
//     * AccessToken 가져오기
//     *
//     * @return : AccessToken
//     * @name : getAccessToken
//     **/
//    @Transactional(rollbackFor = Exception.class)
//    public String getAccessToken(String membNo) throws Exception {
//        String accessToken = "";
//        try {
//            accessToken = jwtUtil.getAccessToken(membNo, AuthMethod.PIN, "System");
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new DefaultException("토큰가져오기 실패");
//        }
//        return accessToken;
//    }




}
