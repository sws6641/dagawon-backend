package com.dagawon.web.common.commSvc.vo;

import com.dagawon.web.common.annotation.NullToEmptyString;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

public class CommCodeVo {

    @Getter
    @Setter
    @Builder
    public static class CommCodeListRes {
        @Schema(description = "공통코드 조회 목록")
        private List<CommCodeInfo> commCodeList = new ArrayList<>();
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommCodeInfo {
        @Schema(description = "그룹코드", example = "JUDT_COURT_CD")
        @Size(max = 50)
        @NullToEmptyString
        String grpCd;

        @Schema(description = "코드", example = "00")
        @Size(max = 50)
        @NullToEmptyString
        String code;

        @Schema(description = "코드명", example = "서울중앙지방법원")
        @Size(max = 100)
        @NullToEmptyString
        String codeNm;

    }

    @Getter
    @Setter
    @Builder
    public static class CustListRes {
        @Schema(description = "회원 조회 목록")
        private List<CustInfo> custList = new ArrayList<>();
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CustInfo {
        @Schema(description = "회원번호", example = "25031200001")
        @Size(max = 11)
        @NullToEmptyString
        String membNo;

        @Schema(description = "사용자 유형", example = "00")
        @Size(max = 2)
        @NullToEmptyString
        String membGbCd;

        @Schema(description = "사용자 명", example = "홍길동")
        @Size(max = 50)
        @NullToEmptyString
        String membNm;

    }
}
