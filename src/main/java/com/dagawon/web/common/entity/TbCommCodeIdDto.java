package com.dagawon.web.common.entity;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link TbCommCodeId}
 */
@Value
public class TbCommCodeIdDto implements Serializable {
    @NotNull
    @Size(max = 30)
    String grpCd;
    @NotNull
    @Size(max = 20)
    String code;
}