package com.dagawon.web.common.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.dagawon.web.common.entity.TbAuthCode}
 */
@Value
public class TbAuthCodeDto implements Serializable {
    LocalDateTime crtDtm;
    String crtMembNo;
    LocalDateTime chgDtm;
    String chgMembNo;
    Long id;
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
    Instant expireDtm;
    Instant usedDtm;
    Long mailLogId;
    @NotNull
    Character usedYn;
}