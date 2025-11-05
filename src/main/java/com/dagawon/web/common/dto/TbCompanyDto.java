package com.dagawon.web.common.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.dagawon.web.common.entity.TbCompany}
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TbCompanyDto implements Serializable {
    LocalDateTime crtDtm;
    String crtMembNo;
    LocalDateTime chgDtm;
    String chgMembNo;
    Long bizNo;
    @NotNull
    @Size(max = 100)
    String companyNm;
    @Size(max = 100)
    String companyEmail;
    @Size(max = 20)
    String companyPhone;
    @Size(max = 255)
    String companyAddr;
    @NotNull
    String useYn;
    @NotNull
    @Size(max = 10)
    String statCd;
}