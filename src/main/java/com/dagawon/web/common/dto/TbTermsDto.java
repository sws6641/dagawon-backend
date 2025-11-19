package com.dagawon.web.common.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.dagawon.web.common.entity.TbTerms}
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TbTermsDto implements Serializable {
    private Long termsId;

    @NotNull
    @Size(max = 50)
    private String termsCode;

    @NotNull
    @Size(max = 10)
    private String version;

    @NotNull
    @Size(max = 200)
    private String title;

    @NotNull
    private String content;

    @NotNull
    @Size(max = 1)
    private String isRequired;

    @NotNull
    private LocalDate startDt;

    private LocalDate endDt;

    private LocalDateTime createdDtm;

    private LocalDateTime updatedDtm;
}