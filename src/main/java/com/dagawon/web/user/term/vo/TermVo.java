package com.dagawon.web.user.term.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

public class TermVo {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Schema(description = "약관 정보 VO")
    public static class TermOutVo {

        @Schema(description = "약관 고유 ID", example = "1")
        private Long termsId;

        @Schema(description = "약관 종류 코드", example = "AGE_LIMIT")
        private String termsCode;

        @Schema(description = "약관 제목", example = "회원가입 약관")
        private String title;

        @Schema(description = "약관 전문", example = "약관 내용 전문")
        private String content;

        @Schema(description = "필수 여부 (Y: 필수, N: 선택)", example = "Y")
        private String isRequired;

        @Schema(description = "약관 사용 위치/용도", example = "login")
        private String usagePlace;
    }
}
