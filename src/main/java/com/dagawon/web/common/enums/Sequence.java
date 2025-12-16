package com.dagawon.web.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * @package      :
 * @name         : Sequence.java
 * @date         :
 * @author       :
 * @version      : 1.0.0
 * @desc         : 20231001 + seqLength (6자리) -> 2023100100006 14자리
 **/


@Getter
@AllArgsConstructor
public enum Sequence {
    MEMB("MEMB", 12),
    IMAGE("IMAGE", 14);

    private final String seqNm;
    private final int seqLen;

}
