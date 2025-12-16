package com.dagawon.web.common.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.dagawon.web.common.entity.TbSequenceId}
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TbSequenceIdDto implements Serializable {
    @NotNull
    private LocalDate seqDate;
    @NotNull
    @Size(max = 100)
    private String seqName;
    @NotNull
    private Integer seqNumber;
}