package com.dagawon.web.common.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.dagawon.web.common.entity.TbPosition}
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TbPositionDto implements Serializable {
    LocalDateTime crtDtm;
    String crtMembNo;
    LocalDateTime chgDtm;
    String chgMembNo;
    Long positionNo;
    @NotNull
    TbCompanyDto bizNo;
    @NotNull
    @Size(max = 50)
    String positionNm;
    @NotNull
    Long orderSeq;
    @NotNull
    String useYn;
    @NotNull
    @Size(max = 10)
    String statCd;
}