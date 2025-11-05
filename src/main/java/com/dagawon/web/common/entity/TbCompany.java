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
@Table(name = "TB_COMPANY")
public class TbCompany  extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BIZ_NO", nullable = false)
    private Long bizNo;

    @Size(max = 100)
    @NotNull
    @Column(name = "COMPANY_NM", nullable = false, length = 100)
    private String companyNm;

    @Size(max = 100)
    @Column(name = "COMPANY_EMAIL", length = 100)
    private String companyEmail;

    @Size(max = 20)
    @Column(name = "COMPANY_PHONE", length = 20)
    private String companyPhone;

    @Size(max = 255)
    @Column(name = "COMPANY_ADDR")
    private String companyAddr;

    @NotNull
    @Column(name = "USE_YN", nullable = false)
    private String useYn;

    @Size(max = 10)
    @NotNull
    @Column(name = "STAT_CD", nullable = false, length = 10)
    private String statCd;

}