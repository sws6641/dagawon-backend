package com.dagawon.web.common.util;

import com.dagawon.web.common.enums.Sequence;
import com.dagawon.web.common.repo.TbSequenceRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
public class BizUtil {

    // Seq 조회
    private final TbSequenceRepository tbSequenceRepository;
    private final EntityManager entityManager;

    /**
     * Sequence 가져오기
     *
     * @param : Sequence 구분값
     * @return : 20230915000001
     * @name : WooriCmnSvc.getSeq
     * @author : tigerBK
     **/
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String getSeq(Sequence seqType) {
        log.debug("seqType:{}", seqType.toString());
        String seq = tbSequenceRepository.getSeq(seqType.getSeqNm(), seqType.getSeqLen());
        // entityManager.flush();
        tbSequenceRepository.flush();
        return seq;
    }

}
