package com.dagawon.web.admin.member.svc;


import com.dagawon.web.common.repo.TbMembRepository;
import com.dagawon.web.config.exception.DefaultException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 어드민
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AdminSvc {
    private final TbMembRepository tbMembRepository;


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

    /**
     * 회원가입 계정 중복 체크
     *
    **/
    public String getDuplicateAccount(String membId, Long bizNo) throws Exception {
        boolean exists = tbMembRepository.existsByMembIdAndBizNo_BizNo(membId, bizNo);
        return exists ? "00" : "01";
    }




}
