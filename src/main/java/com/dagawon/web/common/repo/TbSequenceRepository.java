package com.dagawon.web.common.repo;


import com.dagawon.web.common.entity.TbSequence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

/**
 * Sequence 조회 레파지토리
 * <p>
 * {@code author} :
 * {@code version} : 1.0.0<p>
 * {@code Package} :
 * {@code name} : TbWoSequenceRepository.java<p>
 * {@code date} :
 **/
public interface TbSequenceRepository extends JpaRepository<TbSequence, java.sql.Date> {

    /**
     * {@code param} : SeqName 별<p>
     * {@code return} : 20230921 + 000001 (6자리) ->  20230925000006 14자리<p>
     * {@code name} : TbWoSequenceRepository.getSeq<p>
     * {@code author} :
     **/
    @Procedure(value = "GetSequence")
    String getSeq(@Param("sequence_name") String seqName, @Param("sequence_length") int seqLength);

    @Procedure(value = "GetImgSequence")
    String getImgSequence(@Param("loanNo") String loanNo, @Param("sequence_length") int seqLength);

}
