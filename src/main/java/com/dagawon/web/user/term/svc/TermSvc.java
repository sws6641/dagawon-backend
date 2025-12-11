package com.dagawon.web.user.term.svc;

import com.dagawon.web.common.entity.TbTerms;
import com.dagawon.web.common.repo.TbTermsRepository;
import com.dagawon.web.user.term.vo.TermVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 약관
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TermSvc {

    private final TbTermsRepository tbTermsRepository;

    /**
     * 약관 상세 조회
     **/
    public List<TermVo.TermOutVo> getSignUpTerms(String usagePlace) throws Exception {

        List<TbTerms> TermList = tbTermsRepository.findByUsagePlace(usagePlace);
        List<TermVo.TermOutVo> outVoList = new ArrayList<>();

        for (TbTerms e : TermList) {
            outVoList.add(
                    TermVo.TermOutVo.builder()
                            .termsId(e.getTermsId())
                            .termsCode(e.getTermsCode())
                            .title(e.getTitle())
                            .content(e.getContent())
                            .isRequired(e.getIsRequired())
                            .usagePlace(e.getUsagePlace())
                            .build()
            );
        }
        return outVoList;
    }
}
