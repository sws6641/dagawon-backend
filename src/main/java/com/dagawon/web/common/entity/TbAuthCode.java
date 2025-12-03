package com.dagawon.web.common.entity;

import com.dagawon.web.common.entity.base.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "TB_AUTH_CODE")
public class TbAuthCode extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AUTH_ID", nullable = false)
    private Long authId;

    @Size(max = 50)
    @NotNull
    @Column(name = "MEMB_EMAIL", nullable = false, length = 50)
    private String membEmail;

    @Size(max = 20)
    @NotNull
    @Column(name = "AUTH_TYPE_CD", nullable = false, length = 20)
    private String authTypeCd;

    @Size(max = 200)
    @NotNull
    @Column(name = "AUTH_CODE", nullable = false, length = 200)
    private String authCode;

    @NotNull
    @Column(name = "EXPIRE_DTM", nullable = false)
    private Instant expireDtm;

    @Column(name = "USED_DTM")
    private Instant usedDtm;

    @Column(name = "MAIL_LOG_ID")
    private Long mailLogId;

    @NotNull
    @ColumnDefault("'N'")
    @Column(name = "USED_YN", nullable = false)
    private Character usedYn;

}