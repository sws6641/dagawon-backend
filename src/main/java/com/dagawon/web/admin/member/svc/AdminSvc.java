package com.dagawon.web.admin.member.svc;


import com.dagawon.web.admin.member.vo.AdminVo;
import com.dagawon.web.common.dto.*;
import com.dagawon.web.common.entity.*;
import com.dagawon.web.common.mapper.*;
import com.dagawon.web.common.repo.*;
import com.dagawon.web.common.util.CustomeModelMapper;
import com.dagawon.web.config.exception.DefaultException;
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

    private final TbDeptMapper tbDeptMapper;
    private final TbMembMapper tbMembMapper;
    private final TbCompanyMapper tbCompanyMapper;
    private final TbMembDeptMapper tbMembDeptMapper;
    private final TbPositionMapper tbPositionMapper;
    private final CustomeModelMapper customeModelMapper;

    private final TbDeptRepository tbDeptRepository;
    private final TbMembRepository tbMembRepository;
    private final TbCompanyRepository tbCompanyRepository;
    private final TbMembDeptRepository tbMembDeptRepository;
    private final TbPositionRepository tbPositionRepository;


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
        boolean exists = tbMembRepository.existsByMembEmailAndBizNo_BizNo(membId, bizNo);
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
        Long membNo = 202511240001L;

        // 사업자 정보 조회
        TbCompany tbCompany = tbCompanyRepository.findById(crtMembReqVo.getBizNo())
                .orElseThrow(() -> new DefaultException("회사 정보를 찾을 수 없습니다. "));
        TbCompanyDto tbCompanyDto = tbCompanyMapper.toDto(tbCompany);


        // 3. DTO 생성
        TbMembDto tbMembDto = TbMembDto.builder()
//                .membNo(membNo)
//                .bizNo(crtMembReqVo.getBizNo())
                .membNm(crtMembReqVo.getMembNm())
                .membEmail(crtMembReqVo.getMembEmail())
                .membPwd("1234")
                .roleCd("USER")
                .statCd("ACTIVE")
                .isAdminYn("N")
                .build();

        TbMemb tbMemb = tbMembMapper.toEntity(tbMembDto);
        tbMemb.setBizNo(tbCompany);

        TbMemb membEntity = tbMembRepository.save(tbMemb);

        // 부서코드 정보 조회
        TbDept tbDept = tbDeptRepository.findById(crtMembReqVo.getDeptNo())
                .orElseThrow(() -> new DefaultException("부서코드 정보를 찾을 수 없습니다. "));
        TbDeptDto tbDeptDto = tbDeptMapper.toDto(tbDept);

        // 직급 정보 조회
        TbPosition tbPosition = tbPositionRepository.findById(crtMembReqVo.getPositionNo())
                .orElseThrow(() -> new DefaultException("직급 정보를 찾을 수 없습니다. "));
        TbPositionDto tbPositionDto = tbPositionMapper.toDto(tbPosition);

        // 5. TbMembDeptDto 생성
        TbMembDeptDto deptDto = TbMembDeptDto.builder()
                .membNo(tbMembMapper.toDto(membEntity))
                .deptNo(tbDeptDto)
                .positionNo(tbPositionDto)
                .mainYn("N")
                .roleCd("USER")
                .useYn("Y")
                .statCd("NORMAL")
                .build();

        // 6. DTO → 엔티티 변환 후 저장
        TbMembDept membDeptEntity = tbMembDeptMapper.toEntity(deptDto);
        tbMembDeptRepository.save(membDeptEntity);


        // 4. 저장

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
