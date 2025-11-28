package com.dagawon.web.common.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.dagawon.web.common.entity.TbMailLog}
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TbMailLogDto implements Serializable {
    LocalDateTime crtDtm;
    String crtMembNo;
    LocalDateTime chgDtm;
    String chgMembNo;
    Long mailLogId;
    @NotNull
    @Size(max = 20)
    String mailTypeCd;
    @NotNull
    @Size(max = 200)
    String fromEmail;
    @NotNull
    @Size(max = 200)
    String toEmail;
    @NotNull
    @Size(max = 255)
    String subject;
    @Size(max = 500)
    String contentSummary;
    @NotNull
    @Size(max = 10)
    String sendStatusYn;
    String errorMsg;
    @NotNull
    LocalDateTime sendDt;
}