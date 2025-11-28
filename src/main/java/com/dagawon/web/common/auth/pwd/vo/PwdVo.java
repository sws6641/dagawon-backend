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
}
