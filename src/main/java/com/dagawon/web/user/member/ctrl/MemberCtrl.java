package com.dagawon.web.user.member.ctrl;

import com.dagawon.web.common.util.CustomeModelMapper;
import com.dagawon.web.common.vo.ResData;
import com.dagawon.web.user.member.svc.MemberSvc;
import com.dagawon.web.user.member.vo.MemberVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


//@Tag(name = "01. 회원", description = "회원 API")
@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberCtrl {

    private final MemberSvc memberSvc;
    private final CustomeModelMapper customeModelMapper;
    private final String AUTH_API_BASE_PATH = "/user/members";

    @Operation(summary = "회원가입 정보 조회", description = "회원가입 정보 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원가입 정보 조회 성공", content = @Content(schema = @Schema(implementation = MemberVo.GetMemberInfoVo.class))),
    })
    @PostMapping(value =  AUTH_API_BASE_PATH +"/{id}")
    public ResponseEntity<?> searchAccessToken(@PathVariable(name = "id") Long id) {
        try{
            MemberVo.GetMemberInfoVo member = memberSvc.getMember(id);
            return ResData.SUCCESS(member , "회원가입 정보 조회 성공");
        }catch (Exception e){
            return ResData.FAIL("회원가입 정보 조회 실패" , e.getMessage());
        }
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
    
}
