package com.dagawon.web.user.member.svc;


import com.dagawon.web.common.dto.TbCompanyDto;
import com.dagawon.web.common.dto.TbMembDto;
import com.dagawon.web.common.entity.TbCompany;
import com.dagawon.web.common.entity.TbMemb;
import com.dagawon.web.common.mapper.TbCompanyMapper;
import com.dagawon.web.common.mapper.TbMembMapper;
import com.dagawon.web.common.repo.TbMembRepository;
import com.dagawon.web.common.util.CustomeModelMapper;
import com.dagawon.web.config.exception.DefaultException;
import com.dagawon.web.user.member.vo.MemberVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 인증 서비스
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MemberSvc {

    private final TbMembMapper tbMembMapper;
    private final TbCompanyMapper tbCompanyMapper;
    private final CustomeModelMapper customeModelMapper;

    private final TbMembRepository membRepository;

    /**
     * 회원가입 정보 조회
     *
     * @return :
     * @name : getAccessToken
     **/
    @Transactional
    public MemberVo.GetMemberInfoVo getMember(Long membNo) throws Exception {
        try {
            TbMemb member = membRepository.findByMembNo(membNo)
                    .orElseThrow(() -> new RuntimeException("해당 회원을 찾을 수 없습니다."));

            TbCompany company = member.getBizNo();


            TbMembDto tbMembDto = tbMembMapper.toDto(member);
            MemberVo.GetMemberInfoVo memberInfoVo = customeModelMapper.mapping(tbMembDto, MemberVo.GetMemberInfoVo.class);

            TbCompanyDto tbCompanyDto = tbCompanyMapper.toDto(company);
            MemberVo.CompanyInfoVo companyInfoVo = customeModelMapper.mapping(tbCompanyDto, MemberVo.CompanyInfoVo.class);

            memberInfoVo.setCompany(companyInfoVo);

            return memberInfoVo;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DefaultException("토큰가져오기 실패");
        }
    }

}
