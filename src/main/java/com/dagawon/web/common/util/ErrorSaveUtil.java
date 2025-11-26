//package com.dagawon.web.common.util;
//
//import com.bankle.common.dto.TbErrorHistDto;
//import com.bankle.common.mapper.TbErrorHistMapper;
//import com.bankle.common.repo.TbErrorHistRepository;
//import com.bankle.common.userAuth.UserAuthSvc;
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StreamUtils;
//import jakarta.servlet.http.Part;
//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.nio.charset.StandardCharsets;
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//
//
///**
// * 에러 이력 저장 서비스
// *
// * @author 박원준
// * @version 1.0
// * @since 2025.03.27
// */
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class ErrorSaveUtil {
//
//    private final TbErrorHistRepository tbCommErrorRepository;
//
//    public void saveErrorLog(HttpServletRequest request, Exception ex, HttpStatus status) {
//        try {
//            // 발생한 클래스 및 메서드, 라인 정보 추출
//            String className = "";
//            if (ex.getStackTrace().length > 0) {
//                StackTraceElement firstElement = ex.getStackTrace()[0];
//                className = firstElement.getClassName() + ".java, "
//                        + firstElement.getMethodName() + " , line: "
//                        + firstElement.getLineNumber();
//            }
//
//            // 요청 파라미터 수집 (multipart/form-data 지원)
//            String paramString = "";
//            if (request.getContentType() != null && request.getContentType().startsWith("multipart/")) {
//                paramString = extractMultipartParams(request);
//            } else {
//                paramString = extractRequestParams(request);
//            }
//
//            // 요청 바디 읽기
//            String requestBody = extractRequestBody(request);
//
//            // 회원 정보 가져오기
//            String membNo;
//            try {
//                membNo = UserAuthSvc.getMembNo();
//            } catch (Exception e) {
//                membNo = "System";
//            }
//
//            // 사용자 정보 및 환경 정보 추출
//            String userAgent = request.getHeader("User-Agent");
//            String ipAddress = request.getRemoteAddr();
//            String referrer = request.getHeader("Referer");
//            String httpMethod = request.getMethod();
//            int statusCode = status.value(); // HTTP 상태 코드
//
//            // 로그 저장
//            var dto = TbErrorHistDto.builder()
//                    .message(ex.getMessage())
//                    .url(request.getRequestURI())
//                    .httpMethod(httpMethod)
//                    .statCd(statusCode)
//                    .parameter(paramString.isEmpty() ? requestBody : paramString)
//                    .classNm(className)
//                    .userAgent(userAgent)
//                    .ipAddr(ipAddress)
//                    .referrer(referrer)
//                    .build();
//
//            tbCommErrorRepository.save(TbErrorHistMapper.INSTANCE.toEntity(dto));
//
//        } catch (Exception e) {
//            log.error("Error logging failed => {}", e.getMessage(), e);
//        }
//    }
//
//    /**
//     * 일반적인 요청 파라미터 추출 (application/x-www-form-urlencoded)
//     */
//    private String extractRequestParams(HttpServletRequest request) {
//        Map<String, String[]> parameterMap = request.getParameterMap();
//        return parameterMap.keySet().stream()
//                .map(key -> key + "=" + Arrays.toString(parameterMap.get(key)))
//                .collect(Collectors.joining(", ", "{'", "'}"));
//    }
//
//    /**
//     * multipart/form-data 요청에서 파라미터 및 파일명 추출
//     */
//    private String extractMultipartParams(HttpServletRequest request) {
//        StringBuilder paramString = new StringBuilder("{");
//        Map<String, String[]> parameterMap = request.getParameterMap();
//        log.debug("parameterMap = {}", parameterMap);
//        try {
//            for (Part part : request.getParts()) {
//                if (part.getContentType() == null) { // 일반 텍스트 데이터 (JSON 포함)
//                    String value = new BufferedReader(new InputStreamReader(part.getInputStream(), StandardCharsets.UTF_8))
//                            .lines().collect(Collectors.joining());
//                    paramString.append("'").append(part.getName()).append("'='").append(value).append("', ");
//                } else { // 파일 데이터
//                    paramString.append("'").append(part.getName()).append("'='").append(part.getSubmittedFileName()).append("', ");
//                }
//            }
//        } catch (Exception e) {
//            log.error("Failed to extract multipart parameters", e);
//        }
//
//        if (paramString.length() > 1) {
//            paramString.setLength(paramString.length() - 2); // 마지막 ", " 제거
//        }
//        paramString.append("}");
//        return paramString.toString();
//    }
//
//    /**
//     * 요청 바디 추출 (JSON 포함)
//     */
//    private String extractRequestBody(HttpServletRequest request) {
//        try {
//            if ("POST".equalsIgnoreCase(request.getMethod()) ||
//                    "PATCH".equalsIgnoreCase(request.getMethod()) ||
//                    "PUT".equalsIgnoreCase(request.getMethod())) {
//                return StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
//            }
//        } catch (Exception e) {
//            log.error("Failed to extract request body", e);
//        }
//        return "";
//    }
//}