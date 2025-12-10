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
@Table(name = "TB_MEMB_DEPT")
public class TbMembDept extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMB_DEPT_NO", nullable = false)
    private Long membDeptNo;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MEMB_NO", nullable = false)
    private TbMemb membNo;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "DEPT_NO", nullable = false)
    private TbDept deptNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POSITION_NO")
    private TbPosition positionNo;

    @Size(max = 1)
    @NotNull
    @Column(name = "MAIN_YN", nullable = false, length = 1)
    private String mainYn;

    @Size(max = 20)
    @NotNull
    @Column(name = "ROLE_CD", nullable = false, length = 20)
    private String roleCd;

    @NotNull
    @Column(name = "USE_YN", nullable = false)
    private String useYn;

    @Size(max = 10)
    @NotNull
    @Column(name = "STAT_CD", nullable = false, length = 10)
    private String statCd;

}