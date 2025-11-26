package com.dagawon.web.common.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.dagawon.web.common.entity.TbMemb}
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TbMembDto implements Serializable {
    LocalDateTime crtDtm;
    String crtMembNo;
    LocalDateTime chgDtm;
    String chgMembNo;
    Long membNo;
    @NotNull
    TbCompanyDto bizNo;
    @NotNull
    @Size(max = 50)
    String membEmail;
    @NotNull
    @Size(max = 50)
    String membNm;
    @Size(max = 100)
    String membExtEmail;
    @Size(max = 20)
    String membPhone;
    @NotNull
    @Size(max = 255)
    String membPwd;
    @NotNull
    @Size(max = 20)
    String roleCd;
    @NotNull
    @Size(max = 10)
    String statCd;
    @NotNull
    String isAdminYn;
    LocalDate birthDt;
    @Size(max = 255)
    String profileImg;
    LocalDateTime lastLoginDtm;
}