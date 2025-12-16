package com.dagawon.web.common.dto;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.dagawon.web.common.entity.TbSequence}
 */
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class TbSequenceDto implements Serializable {
    TbSequenceIdDto id;
}