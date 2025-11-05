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
@Table(name = "TB_DEPT")
public class TbDept extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEPT_NO", nullable = false)
    private Long deptNo;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "BIZ_NO", nullable = false)
    private TbCompany bizNo;

    @Size(max = 100)
    @NotNull
    @Column(name = "DEPT_NM", nullable = false, length = 100)
    private String deptNm;

    @Size(max = 255)
    @Column(name = "DEPT_DESC")
    private String deptDesc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UPPER_DEPT_NO")
    private TbDept upperDeptNo;

    @NotNull
    @Column(name = "USE_YN", nullable = false)
    private String useYn;

    @Size(max = 10)
    @NotNull
    @Column(name = "STAT_CD", nullable = false, length = 10)
    private String statCd;

}