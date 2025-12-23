package com.dagawon.web.user.member.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MemberVo {

    @Getter
    @Setter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetMemberInfoVo {
        private Long membNo;
        private String membEmail;
        private String membNm;
        private String membExtEmail;
        private String membPhone;
        private String roleCd;
        private String statCd;
        private String isAdminYn;
        private LocalDate birthDt;
        private String profileImg;
        private LocalDateTime lastLoginDtm;
        private CompanyInfoVo company;
    }

    @Getter
    @Setter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CompanyInfoVo {
        private Long bizNo;
        private String companyNm;
        private String companyEmail;
        private String companyPhone;
        private String companyAddr;
        private String useYn;
        private String statCd;
    }

    @Getter
    @Setter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ModifyMemberReq {
        @NotEmpty(message = "회원 이메일은 필수 항목입니다.")
        @Schema(name = "membEmail", example = "pwj5845")
        private String membEmail;
        @NotEmpty(message = "패스워드는 필수 항목입니다.")
        @Schema(description = "패스워드", example = "abcde12")
        @Size(min = 8 , max = 32 , message = "패스워드는 8자리에서 32자리 까지 입력가능합니다.")
        private String membPwd;
    }





}
