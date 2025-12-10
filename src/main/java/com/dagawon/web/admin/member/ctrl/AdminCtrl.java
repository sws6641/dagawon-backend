package com.dagawon.web.admin.member.ctrl;

import com.dagawon.web.admin.member.svc.AdminSvc;
import com.dagawon.web.admin.member.vo.AdminVo;
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
import org.springframework.web.bind.annotation.*;


@Tag(name = "어드민 - 회원가입", description = "회원가입")
@Slf4j
@RestController
@RequiredArgsConstructor
public class AdminCtrl {

//    private final MemberSvc memberSvc;
//    private final CustomeModelMapper customeModelMapper;
    private final String ADMIN_API_BASE_PATH = "/admin";
    private final AdminSvc adminSvc;
//
//    @Operation(summary = "TEST API AccessToken 조회 ", description = "홍길동 : 202403170004 , 박남정 : 202403170005")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = " AccessToken 조회 성공", content = @Content(schema = @Schema(implementation = String.class))),
//    })
//    @PostMapping(value =  AUTH_API_BASE_PATH +"/searchaccesstoken/{membNo}")
//    public ResponseEntity<?> searchAccessToken(@PathVariable(name = "membNo") String membNo) {
//        try{
//            String token = memberSvc.getAccessToken(membNo);
//            return ResData.SUCCESS(token , "AccessToken 토큰 가져오기 성공");
//        }catch (Exception e){
//            return ResData.FAIL("AccessToken 토큰 가져오기 실패" , e.getMessage());
//        }
//    }

    @Operation(summary = "계정 중복 체크", description = """
            [ REQUEST ]
            - membId : gejeong
            - bizNo : 1234567890
            
            [ RESPONSE ]
            
            code
            - 00 : 조회 성공
            
            msg
            - data > resCd의 00~99까지의 '(괄호 안)' 값
            
            data
            - 00 : 사용자 이메일 존재 (회원가입 불가)
            - 01 : 사용자 이메일 미존재 (회원가입 불가)
            """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원가입 중복체크 성공", content = @Content(schema = @Schema(implementation = String.class))),
    })
    @GetMapping(value =  ADMIN_API_BASE_PATH +"/members")
    public ResponseEntity<?> searchDuplicateAccount(@RequestParam(name = "membId") String membId, @RequestParam(name = "bizNo") Long bizNo) {
        try{
            String result = adminSvc.getDuplicateAccount(membId, bizNo);

            return switch (result) {
                case "00" -> ResData.SUCCESS("00", "이미 존재하는 회원입니다.");
                case "01" -> ResData.SUCCESS("01", "해당 아이디로 가입이 가능합니다.");
                default   -> ResData.FAIL("99", "처리할 수 없습니다");
            };

        }catch (Exception e){
            return ResData.FAIL("회원가입 중복체크 실패" , e.getMessage());
        }
    }

    @Operation(summary = "회원정보 저장", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원정보 저장 성공", content = @Content(schema = @Schema(implementation = String.class))),
    })
    @PostMapping(value =  ADMIN_API_BASE_PATH +"/members")
    public ResponseEntity<?> searchDuplicateAccount(@RequestBody AdminVo.CrtMembReq crtMembReqVo) {
        try{
            String result = adminSvc.crtMembers(crtMembReqVo);
            return ResData.SUCCESS(result);

        }catch (Exception e){
            e.printStackTrace();
            return ResData.FAIL("회원정보 저장 실패" , e.getMessage());
        }
    }


}
