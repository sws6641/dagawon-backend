package com.dagawon.web.common.entity;

import com.dagawon.web.common.entity.base.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TB_POSITION")
public class TbPosition extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POSITION_NO", nullable = false)
    private Long positionNo;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "BIZ_NO", nullable = false)
    private TbCompany bizNo;

    @Size(max = 50)
    @NotNull
    @Column(name = "POSITION_NM", nullable = false, length = 50)
    private String positionNm;

    @NotNull
    @Column(name = "ORDER_SEQ", nullable = false)
    private Long orderSeq;

    @NotNull
    @Column(name = "USE_YN", nullable = false)
    private String useYn;

    @Size(max = 10)
    @NotNull
    @Column(name = "STAT_CD", nullable = false, length = 10)
    private String statCd;

}