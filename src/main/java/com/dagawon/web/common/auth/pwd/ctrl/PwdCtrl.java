package com.dagawon.web.common.auth.pwd.ctrl;

import com.dagawon.web.common.auth.pwd.svc.PwdSvc;
import com.dagawon.web.common.vo.ResData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "비빌번호 찾기", description = "비빌번호 찾기")
@Slf4j
@RestController
@RequiredArgsConstructor
public class PwdCtrl {

    private final String PWD_API_BASE_PATH = "auth/pwd";
    private final PwdSvc pwdSvc;

    @Operation(summary = "비빌번호 찾기 인증코드 메일 발송 API", description = """
            """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "비빌번호 찾기 인증코드 메일 발송 성공", content = @Content(schema = @Schema(implementation = String.class))),
    })
    @GetMapping(value =  PWD_API_BASE_PATH +"/send-code")
    public ResponseEntity<?> sendAuthCodeMail() {
        try{

            pwdSvc.sendAuthCodeMail();
            return ResData.SUCCESS("01", "해당 아이디로 가입이 가능합니다.");

        }catch (Exception e){
            return ResData.FAIL("비빌번호 찾기 인증코드 메일 발송 실패" , e.getMessage());
        }
    }



}
