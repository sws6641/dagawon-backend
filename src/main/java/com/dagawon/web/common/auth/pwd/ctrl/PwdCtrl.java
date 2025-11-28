package com.dagawon.web.common.auth.pwd.ctrl;

import com.dagawon.web.common.auth.pwd.svc.PwdSvc;
import com.dagawon.web.common.auth.pwd.vo.PwdVo;
import com.dagawon.web.common.vo.ResData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "비빌번호 찾기", description = "비빌번호 찾기")
@Slf4j
@RestController
@RequiredArgsConstructor
public class PwdCtrl {

    private final String PWD_API_BASE_PATH = "auth/pwd";
    private final PwdSvc pwdSvc;

    @Operation(
            summary = "비밀번호 찾기 인증코드 메일 발송 API",
            description = """
                입력한 회원 ID를 기준으로 등록된 이메일 주소로 
                비밀번호 재설정을 위한 6자리 인증코드를 발송합니다.

                - 회원 ID가 존재하지 않을 경우: 오류 반환
                - 회원 이메일이 등록되지 않은 경우: 오류 반환
                - 정상적으로 인증코드가 발송되면 `code: 00` 을 반환합니다.
                """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "메일 발송 성공 (code: 00)",
                    content = @Content(schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "요청값 누락, 회원 미존재 또는 이메일 미등록",
                    content = @Content(schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "서버 내부 오류 또는 메일 발송 실패",
                    content = @Content(schema = @Schema(implementation = String.class))
            )
    })
    @PostMapping(PWD_API_BASE_PATH + "/send-code")
    public ResponseEntity<?> sendAuthCodeMail(@RequestBody @Valid PwdVo.SendAuthCodeMailReq req) throws Exception {
        pwdSvc.sendAuthCodeMail(req);
        return ResData.SUCCESS("00", "메일 발송 성공");
    }



}
