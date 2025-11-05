package com.dagawon.web.common.dto;

import com.dagawon.web.common.entity.TbMembDept;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link TbMembDept}
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TbMembDeptDto implements Serializable {
    LocalDateTime crtDtm;
    String crtMembNo;
    LocalDateTime chgDtm;
    String chgMembNo;
    Long membDeptNo;
    @NotNull
    TbMembDto membNo;
    @NotNull
    TbDeptDto deptNo;
    TbPositionDto positionNo;
    @NotNull
    String mainYn;
    @NotNull
    @Size(max = 20)
    String roleCd;
    @NotNull
    String useYn;
    @NotNull
    @Size(max = 10)
    String statCd;
}