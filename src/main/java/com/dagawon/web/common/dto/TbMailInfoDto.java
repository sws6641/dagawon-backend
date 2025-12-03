package com.dagawon.web.common.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.dagawon.web.common.entity.TbMailInfo}
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TbMailInfoDto implements Serializable {
    LocalDateTime crtDtm;
    String crtMembNo;
    LocalDateTime chgDtm;
    String chgMembNo;
    Long mailId;
    Long bizNo;
    @NotNull
    @Size(max = 2)
    String acntCd;
    @NotNull
    @Size(max = 100)
    String userNm;
    @NotNull
    @Size(max = 100)
    String email;
    @NotNull
    String useYn;
}