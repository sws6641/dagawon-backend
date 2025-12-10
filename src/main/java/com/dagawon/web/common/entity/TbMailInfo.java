package com.dagawon.web.common.entity;

import com.dagawon.web.common.entity.base.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "TB_MAIL_INFO")
public class TbMailInfo extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MAIL_ID", nullable = false)
    private Long mailId;

    @NotNull
    @Column(name = "BIZ_NO", nullable = false)
    private Long bizNo;

    @Size(max = 2)
    @NotNull
    @Column(name = "ACNT_CD", nullable = false, length = 2)
    private String acntCd;

    @Size(max = 100)
    @NotNull
    @Column(name = "USER_NM", nullable = false, length = 100)
    private String userNm;

    @Size(max = 100)
    @NotNull
    @Column(name = "EMAIL", nullable = false, length = 100)
    private String email;

    @NotNull
    @Column(name = "USE_YN", nullable = false)
    private String useYn;

}