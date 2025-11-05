package com.dagawon.web.common.dto;

import com.dagawon.web.common.entity.TbDept;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.dagawon.web.common.entity.TbDept}
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TbDeptDto implements Serializable {
    LocalDateTime crtDtm;
    String crtMembNo;
    LocalDateTime chgDtm;
    String chgMembNo;
    Long deptNo;
    @NotNull
    TbCompanyDto bizNo;
    @NotNull
    @Size(max = 100)
    String deptNm;
    @Size(max = 255)
    String deptDesc;
    TbDept upperDeptNo;
    @NotNull
    String useYn;
    @NotNull
    @Size(max = 10)
    String statCd;
}