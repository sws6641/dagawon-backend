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
        @NotEmpty(message = "ID는 필수 항목입니다.")
        @Schema(name = "id", example = "sws6641")
        String membId;
    }
}
