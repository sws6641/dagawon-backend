package com.dagawon.web.user.term.ctrl;

import com.dagawon.web.common.vo.ResData;
import com.dagawon.web.user.term.svc.TermSvc;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "사용자 - 약관", description = "약관")
@Slf4j
@RestController
@RequiredArgsConstructor
public class TermCtrl {

    private final String USER_API_BASE_PATH = "/user";
    private final TermSvc termSvc;

    @Operation(summary = "약관 불러오기", description = """
            [ REQUEST ]
            - usagePlace : login
            
            [ RESPONSE ]
            code
            - 00 : 조회 성공
            
            msg
            - data > resCd의 00~99까지의 '(괄호 안)' 값
            
            data
            - 00 : 약관 불러오기 성공
            - 01 : 약관 불러오기 실패
            """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "약관 상세조회 성공", content = @Content(schema = @Schema(implementation = String.class))),
    })
    @GetMapping(value =  USER_API_BASE_PATH +"/terms")
    public ResponseEntity<?> searchDuplicateAccount(@RequestParam(name = "usagePlace") String usagePlace) {
        try{

            return ResData.SUCCESS( termSvc.getSignUpTerms(usagePlace), "약관 상세조회 성공");

        }catch (Exception e){
            return ResData.FAIL("약관 상세조회 실패" , e.getMessage());
        }
    }
}
