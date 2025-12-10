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
        @NotEmpty(message = "회원명은 필수 항목입니다.")
        @Schema(name = "membNm", example = "송원섭")
        private String membNm;

        @NotEmpty(message = "회원 이메일은 필수 항목입니다.")
        @Schema(name = "membEmail", example = "sws6641")
        private String membEmail;

        @NotEmpty(message = "구분 코드는 필수 항목입니다.")
        @Schema(name = "deptNo", example = "1")
        private Long deptNo;

        @NotEmpty(message = "구분 코드는 필수 항목입니다.")
        @Schema(name = "positionNo", example = "1")
        private Long positionNo;

        @NotEmpty(message = "사업자번호는 필수 항목입니다.")
        @Schema(name = "bizNo", example = "1234567890")
        private Long bizNo;
    }





}
