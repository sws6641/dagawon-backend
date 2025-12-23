package com.dagawon.web.common.entity;

import com.dagawon.web.common.annotation.Encrypted;
import com.dagawon.web.common.converter.EncryptConverter;
import com.dagawon.web.common.converter.HashConverter;
import com.dagawon.web.common.entity.base.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "TB_MEMB")
public class TbMemb extends BaseTimeEntity {
    @Id
    @Column(name = "MEMB_NO", nullable = false)
    private Long membNo;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "BIZ_NO", nullable = false)
    private TbCompany bizNo;

    @Size(max = 50)
    @NotNull
    @Column(name = "MEMB_EMAIL", nullable = false, length = 50)
    private String membEmail;

    @Size(max = 50)
    @NotNull
    @Column(name = "MEMB_NM", nullable = false, length = 50)
    private String membNm;

    @Size(max = 100)
    @Column(name = "MEMB_EXT_EMAIL", length = 100)
    private String membExtEmail;

    @Size(max = 255)
    @Encrypted
    @Convert(converter = EncryptConverter.class) //양방향 암호화
    @Column(name = "MEMB_PHONE", length = 255)
    private String membPhone;

    @Size(max = 255)
    @NotNull
    @Convert(converter = HashConverter.class) //단방향 암호화
    @Column(name = "MEMB_PWD", nullable = false)
    private String membPwd;

    @Size(max = 20)
    @NotNull
    @Column(name = "ROLE_CD", nullable = false, length = 20)
    private String roleCd;

    @Size(max = 10)
    @NotNull
    @Column(name = "STAT_CD", nullable = false, length = 10)
    private String statCd;

    @NotNull
    @Column(name = "IS_ADMIN_YN", nullable = false)
    private String isAdminYn;

    @Column(name = "BIRTH_DT")
    private LocalDate birthDt;

    @Size(max = 255)
    @Column(name = "PROFILE_IMG")
    private String profileImg;

    @Column(name = "LAST_LOGIN_DTM")
    private LocalDateTime lastLoginDtm;

}