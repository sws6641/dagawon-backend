package com.dagawon.web.user.member.vo;

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





}
