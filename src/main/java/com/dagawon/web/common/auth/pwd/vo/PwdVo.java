package com.dagawon.web.common.auth.pwd.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

public class PwdVo {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SendAuthCodeMailReq {
        @NotEmpty(message = "회원 이메일은 필수 항목입니다.")
        @Schema(description = "회원이메일", example = "pwj5845")
        private String membEmail;
    }


    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ModifyPwdReq {
        @NotEmpty(message = "회원 이메일은 필수 항목입니다.")
        @Schema(name = "membEmail", example = "pwj5845")
        private String membEmail;
        @NotEmpty(message = "수정 패스워드는 필수 항목입니다.")
        @Schema(description = "수정 패스워드", example = "abcde12")
        @Size(min = 8 , max = 32 , message = "패스워드는 8자리에서 32자리 까지 입력가능합니다.")
        private String modifyPwd;
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
