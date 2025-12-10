package com.dagawon.web.common.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.dagawon.web.common.entity.TbAuthCode}
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TbAuthCodeDto implements Serializable {
    LocalDateTime crtDtm;
    String crtMembNo;
    LocalDateTime chgDtm;
    String chgMembNo;
    Long authId;
    @NotNull
    @Size(max = 50)
    String membEmail;
    @NotNull
    @Size(max = 20)
    String authTypeCd;
    @NotNull
    @Size(max = 200)
    String authCode;
    @NotNull
    LocalDateTime expireDtm;
    LocalDateTime usedDtm;
    Long mailLogId;
    @NotNull
    String usedYn;
}