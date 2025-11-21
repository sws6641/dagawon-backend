package com.dagawon.web.admin.member.svc;


import com.dagawon.web.admin.member.vo.AdminVo;
import com.dagawon.web.common.mapper.TbMembMapper;
import com.dagawon.web.common.repo.TbMembRepository;
import com.dagawon.web.common.util.CustomeModelMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 어드민
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AdminSvc {

    private final TbMembMapper tbMembMapper;
    private final CustomeModelMapper customeModelMapper;

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

    /**
     * 회원정보 저장
     *
     **/
    @Transactional
    public String crtMembers(AdminVo.CrtMembReq crtMembReqVo) {

        // TODO: 계정 중복 체크는 위 서비스 사용
        // 1. 이메일 중복 체크
//        if (tbMembRepository.existsByEmailId(crtMembReqVo.getEmailId())) {
//            throw new IllegalArgumentException("이미 사용 중인 이메일 아이디입니다.");
//        }

        // TODO: 사번 생성은 프로시저 사용?
        // 2. 사번 생성
//        String empNo = generateEmpNo();

        // 3. DTO 생성
//        TbMembDto dto = TbMembDto.builder()
//                .membNm(crtMembReqVo.getUserName())
//                .membEmail(crtMembReqVo.getEmailId())
//                .deptCd(crtMembReqVo.getDeptCd())
//                .rankCd(crtMembReqVo.getRankCd())
//                .empNo(empNo)
//                .build();

        // TODO: 부서코드는 memb_dept 테이블에 저장해야함(dept_no) -> 부서코드 리스트는 dept 테이블에 같은 사업자로 존재
        // TODO: 직급코드는 memb_dept 테이블에 저장해야함(position_no) -> 직급 리스트는 position 테이블에 같은 사업자로 존재

        // 4. 저장
//        tbMembRepository.save(tbMembMapper.toEntity(dto));

//        return empNo;   // 생성된 사번 반환
        return "";   // 생성된 사번 반환
    }

//    private String generateEmpNo() {
//        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
//
//        // 오늘 날짜에 해당하는 seq 체크
//        int countToday = tbMembRepository.countByEmpNoStartingWith("EMP" + date);
//
//        String seq = String.format("%04d", countToday + 1);
//        return "EMP" + date + seq;
//    }


}
