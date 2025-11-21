package com.dagawon.web.admin.member.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

public class AdminVo {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CrtMembReq {
        @NotEmpty(message = "ID는 필수 항목입니다.")
        @Schema(name = "id", example = "sws6641")
        private String membNm;

        @NotEmpty(message = "PASSWORD는 필수 항목입니다.")
        @Schema(name = "pwd", example = "1234")
        private String membEmail;

        @NotEmpty(message = "구분 코드는 필수 항목입니다.")
        @Schema(name = "membGbCd", example = "00")
        private String deptCd;

        @NotEmpty(message = "구분 코드는 필수 항목입니다.")
        @Schema(name = "membGbCd", example = "00")
        private String rankCd;
    }





}
