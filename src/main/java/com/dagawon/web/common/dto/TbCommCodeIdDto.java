package com.dagawon.web.common.dto;

import com.dagawon.web.common.entity.TbCommCodeId;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link TbCommCodeId}
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TbCommCodeIdDto implements Serializable {
    @NotNull
    @Size(max = 30)
    String grpCd;
    @NotNull
    @Size(max = 20)
    String code;
}