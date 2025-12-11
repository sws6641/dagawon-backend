package com.dagawon.web.common.auth.pwd.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

public class PwdVo {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SendAuthCodeMailReq {
        @NotEmpty(message = "회원 이메일은 필수 항목입니다.")
        @Schema(name = "membEmail", example = "pwj5845")
        String membEmail;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VerifyAuthCodeMailReq {
        @NotEmpty(message = "회원 이메일은 필수 항목입니다.")
        @Schema(name = "membEmail", example = "gejeong@abc.co.kr")
        String membEmail;

        @NotEmpty(message = "인증번호 필수 항목입니다.")
        @Schema(name = "authCode", example = "aAbB127@")
        String authCode;
    }
}
