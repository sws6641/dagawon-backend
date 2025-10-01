package com.dagawon.web.admin.member.ctrl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//@Tag(name = "01. 회원", description = "회원 API")
@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberCtrl {

//    private final MemberSvc memberSvc;
//    private final CustomeModelMapper customeModelMapper;
//    private final String AUTH_API_BASE_PATH = "/auth";
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



}
