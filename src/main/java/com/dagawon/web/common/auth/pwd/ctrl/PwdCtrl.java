package com.dagawon.web.common.auth.pwd.ctrl;

import com.dagawon.web.common.auth.pwd.svc.PwdSvc;
import com.dagawon.web.common.auth.pwd.vo.PwdVo;
import com.dagawon.web.common.vo.ResData;
import com.dagawon.web.config.exception.BadRequestException;
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

    @Operation(summary = "이메일 인증코드 검증", description = """
            [ REQUEST ]
            - membEmail : gejeong@abc.co.kr
            - authCode : a!23Fe1
            
            [ RESPONSE ]
            
            code
            - 00 : 인증번호가 일치
            - 01 : 인증코드 데이터 미존재
            - 02 : 인증시간 만료
            - 03 : 인증번호 불일치
            
            msg
            - data > resCd의 00~99까지의 '(괄호 안)' 값
            
            data
            - 00 : 사용자 이메일 존재 (회원가입 불가)
            - 01 : 사용자 이메일 미존재 (회원가입 불가)
            """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "인증코드 검증 성공", content = @Content(schema = @Schema(implementation = String.class))),
    })
    @PostMapping(value =  PWD_API_BASE_PATH +"/verify-code")
    public ResponseEntity<?> verifyAuthCodeMail(@RequestBody @Valid PwdVo.VerifyAuthCodeMailReq req) {
        try{
            String value = pwdSvc.verifyAuthCodeMail(req);

            return switch (value) {
                case "00" -> ResData.SUCCESS("", "인증번호가 일치");
                case "01" -> ResData.SUCCESS("", "인증코드 데이터 미존재");
                case "02" -> ResData.SUCCESS("", "인증시간 만료");
                case "03" -> ResData.SUCCESS("", "인증번호 불일치");
                default -> throw new BadRequestException("인증번호 검증 실패");
            };

        }catch (Exception e){
            return ResData.FAIL("인증코드 검증 실패" , e.getMessage());
        }
    }



}
