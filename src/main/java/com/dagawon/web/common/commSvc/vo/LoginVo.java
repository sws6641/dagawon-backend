package com.dagawon.web.common.commSvc.vo;

import com.dagawon.web.common.annotation.NullToEmptyString;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;


public class LoginVo {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginReq {
        @NotEmpty(message = "ID는 필수 항목입니다.")
        @Schema(name = "id", example = "sbsh")
        String id;

        @NotEmpty(message = "PASSWORD는 필수 항목입니다.")
        @Schema(name = "pwd", example = "sbsh")
        String pwd;

        @NotEmpty(message = "구분 코드는 필수 항목입니다.")
        @Schema(name = "membGbCd", example = "00")
        String membGbCd;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginRes {

        @Schema(description = "로그인 중 여부", example = "false")
        private boolean lognYn = false;

        @Schema(description = "결과코드", example = "00")
        @NullToEmptyString
        private String resCd;

        @Schema(description = "사용자 번호", example = "2503120001")
        @NullToEmptyString
        private String membNo;

        @Schema(description = "사용자명", example = "홍길동")
        @NullToEmptyString
        private String membNm;

        @Schema(description = "사용자 구분 코드", example = "00")
        @NullToEmptyString
        private String membGbCd;

        @Schema(description = "사용자 구분 명", example = "금융기관")
        @NullToEmptyString
        private String membGbNm;

        @Schema(description = "휴대폰번호", example = "01012345678")
        @NullToEmptyString
        private String membHpno;

        @Schema(description = "상태코드", example = "10")
        @NullToEmptyString
        private String statCd;

        @Schema(description = "상태명", example = "승인")
        @NullToEmptyString
        private String statNm;

        @Schema(description = "accessToken", example = "")
        @NullToEmptyString
        private String accessToken;

    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TokenReq {
        String loginId;
        String membNo;
        String membNm;
        String bizNo;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PwdChgReq {
        @NotEmpty(message = "membNo는 필수 항목입니다.")
        @Schema(name = "membNo", example = "20250403003")
        String membNo;

        @NotEmpty(message = "PASSWORD는 필수 항목입니다.")
        @Schema(name = "pwd", example = "mng12345")
        String pwd;

        @NotEmpty(message = "새로운 PASSWORD는 필수 항목입니다.")
        @Schema(name = "newPwd", example = "mng11111")
        String newPwd;

        @NotEmpty(message = "확인용 PASSWORD는 필수 항목입니다.")
        @Schema(name = "reNewPwd", example = "mng11111")
        String reNewPwd;
    }
}
