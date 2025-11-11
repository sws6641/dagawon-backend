package com.dagawon.web.common.ctrl;

import com.dagawon.web.common.commSvc.LoginSvc;
import com.dagawon.web.common.commSvc.vo.LoginVo;
import com.dagawon.web.common.vo.ResData;
import com.dagawon.web.config.exception.BadRequestException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "공통 - 로그인", description = "로그인 관련 기능")
@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginCtrl {

    private final LoginSvc loginSvc;

    @Operation(summary = "로그인 ", description = """
            [ REQUEST ]
            
            - id : sbsh
            - pwd : sbsh
            - membGbCd : 00
            
            [ RESPONSE ]
            
            code
            - 00 : 성공
            - 99 : 실패
            
            msg
            - data > resCd의 00~99까지의 '(괄호 안)' 값
            
            data
            - lognYn : 로그인 중 여부
            - resCd : 00(사용자 로그인 성공), 01(비밀번호가 틀렸습니다.), 99(승인된 사용자가 존재하지 않습니다.)
            - statNm (statCd) : 승인(10), 잠김(20), 탈퇴(30)
            - membGbNm (membGbCd) : 금융기관(00), 법무대리인(10), 관리자(20)
            """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "login 성공", content = @Content(schema = @Schema(implementation = LoginVo.LoginRes.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content(schema = @Schema(implementation = LoginVo.LoginRes.class)))
    })
    @PostMapping(value = "/auth/login")
    public ResponseEntity<?> loginCtrl(@RequestBody LoginVo.LoginReq loginReqVo, HttpServletResponse response) throws Exception {

        log.debug(" ===== 신협 로그인 : {} ===== ", loginReqVo.getId());

        LoginVo.LoginRes outSvo = loginSvc.getLogin(loginReqVo, response);

//        return ResData.SUCCESS("00", "로그인");

        return switch (outSvo.getResCd()) {
            case "00" -> ResData.SUCCESS(outSvo, "사용자 로그인 성공");
            case "01" -> ResData.FAIL(outSvo, "비밀번호가 틀렸습니다.");
            case "99" -> ResData.FAIL(outSvo, "승인된 사용자가 존재하지 않습니다.");
            default -> throw new BadRequestException("사용자 로그인 실패");
        };

    }

    @Operation(summary = "로그아웃", description = """
            [ REQUEST ]
            - 요청에 필요한 정보 없음
            
            [ RESPONSE ]
            
            code
            - 00 : 성공
            - 99 : 실패
            
            msg
            - data 00~99까지의 '(괄호 안)' 값
            
            data
            - 00 (사용자 로그아웃 성공)
            - 99 (사용자 로그아웃 실패)
            """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "logout 성공", content = @Content(schema = @Schema(implementation = ResData.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content(schema = @Schema(implementation = ResData.class)))
    })
    @PostMapping(value = "/auth/logout")
    public ResponseEntity<?> logoutCtrl(HttpServletResponse response) throws Exception {
        loginSvc.getLogout(response);
        return ResData.SUCCESS("00", "사용자 로그아웃 성공");
    }

    @Operation(summary = "프로필", description = """
            [ REQUEST ]
            - 요청에 필요한 정보 없음
            
            [ RESPONSE ]
            
            code
            - 00 : 성공
            - 99 : 실패
            
            msg
            - (code)00 : 프로필 이미지 조회 성공
            - (code)99 : 프로필 이미지 조회 실패
            
            data
            - 프로필 이미지 정보
            """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "프로필 이미지 조회 성공", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content(schema = @Schema(implementation = ResData.class)))
    })
    @GetMapping(value = "/auth/profile")
    public ResponseEntity<?> profileCtrl() throws Exception {
//        ImageVo.ImageRes profile = loginSvc.getOfficeProfile();
//        return ResData.SUCCESS(profile, "프로필 이미지 조회 성공");
            return ResData.SUCCESS("00", "프로필");
    }

    @Operation(summary = "비밀번호 변경 ", description = """
            [ REQUEST ]
            
            - membNo : 20250403003
            - pwd : mng12345
            - newPwd : mng11111
            - reNewPwd : mng11111
            
            [ RESPONSE ]
            
            code
            - 00 : 성공 (data : 00)
            - 99 : 실패 (data : 01 ~ 99)
            
            msg
            - data : 00~99까지의 '(괄호 안)' 값
            
            data
              - 00 (비밀번호가 변경되었습니다.)\n
              - 01 (현재 비밀번호와 일치하지 않습니다.)\n
              - 02 (8~20자 영문, 숫자를 모두 포함한 비밀번호를 입력해 주세요.)\n
              - 03 (비밀번호가 일치하지 않습니다.)\n
              - 04 (이전과 동일한 비밀번호입니다.)\n
              - 05 (비밀번호를 입력해주세요.)\n
              - 99 (사용자가 존재하지 않습니다.)
            """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "비밀번호가 변경되었습니다.", content = @Content(schema = @Schema(implementation = ResData.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content(schema = @Schema(implementation = ResData.class)))
    })
    @PatchMapping(value = "/auth/password/change")
    public ResponseEntity<?> chgPwdCtrl(@RequestBody LoginVo.PwdChgReq pwdChgReq) throws Exception {

        log.debug(" ===== 신협 비밀번호 변경 : {} ===== ", pwdChgReq.getMembNo());

//        String outSvo = loginSvc.edtPwd(pwdChgReq);

        return ResData.SUCCESS("00", "프로필");

//        return switch (outSvo) {
//            case "00" -> ResData.SUCCESS("00", "비밀번호가 변경되었습니다.");
//            case "01" -> ResData.FAIL("01", "현재 비밀번호와 일치하지 않습니다.");
//            case "02" -> ResData.FAIL("02", "8~20 자 영문, 숫자를 모두 포함한 비밀번호를 입력해 주세요.");
//            case "03" -> ResData.FAIL("03", "비밀번호가 일치하지 않습니다.");
//            case "04" -> ResData.FAIL("04", "이전과 동일한 비밀번호 입니다.");
//            case "05" -> ResData.FAIL("05", "비밀번호를 입력해주세요.");
//            case "99" -> ResData.FAIL("99", "사용자가 존재하지 않습니다.");
//            default -> throw new BadRequestException("비밀번호 변경 실패");
//        };
    }
}
