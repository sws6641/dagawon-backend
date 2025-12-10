package com.dagawon.web.common.entity;

import com.dagawon.web.common.entity.base.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "TB_MAIL_LOG")
public class TbMailLog extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MAIL_LOG_ID", nullable = false)
    private Long mailLogId;

    @Size(max = 20)
    @NotNull
    @Column(name = "MAIL_TYPE_CD", nullable = false, length = 20)
    private String mailTypeCd;

    @Size(max = 200)
    @NotNull
    @Column(name = "FROM_EMAIL", nullable = false, length = 200)
    private String fromEmail;

    @Size(max = 200)
    @NotNull
    @Column(name = "TO_EMAIL", nullable = false, length = 200)
    private String toEmail;

    @Size(max = 255)
    @NotNull
    @Column(name = "SUBJECT", nullable = false)
    private String subject;

    @Column(name = "CONTENT")
    private String content;

    @Size(max = 10)
    @NotNull
    @Column(name = "SEND_STATUS_YN", nullable = false, length = 10)
    private String sendStatusYn;

    @Lob
    @Column(name = "ERROR_MSG")
    private String errorMsg;

    @NotNull
    @Column(name = "SEND_DT", nullable = false)
    private LocalDateTime sendDt;

}