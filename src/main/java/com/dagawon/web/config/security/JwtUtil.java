package com.dagawon.web.config.security;

import com.dagawon.web.common.commSvc.vo.LoginVo;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil {

    @Value("${app.auth.token-secret}")
    private String secret;
    private static final Long ACCESS_TOKEN_EXPIRATION_TIME = 200L * 24 * 60 * 60 * 1000;    // 200일
    private static final Long REFRESH_TOKEN_EXPIRATION_TIME = 365L * 24 * 60 * 60 * 1000;   // 365일

    public static final String LOGIN_ID = "loginId";
    public static final String MEMB_NO = "membNo";
    public static final String MEMB_NM = "membNm";
    public static final String BIZ_NO = "bizNo";
    public static final String JWT_ID = "jti";


    public String getUserAccessToken(LoginVo.TokenReq tokenReq) {
        return generateToken("ACCESS_TOKEN", ACCESS_TOKEN_EXPIRATION_TIME, tokenReq);
    }

    public String getUserRefreshToken(LoginVo.TokenReq tokenReq) {
        return generateToken("REFRESH_TOKEN", REFRESH_TOKEN_EXPIRATION_TIME, tokenReq);
    }

    public String generateToken(String subject, Long expiration, LoginVo.TokenReq tokenReq) {
        HashMap<String, Object> claim = new HashMap<>();
        claim.put(LOGIN_ID, tokenReq.getLoginId());
        claim.put(MEMB_NO, tokenReq.getMembNo());
        claim.put(MEMB_NM, tokenReq.getMembNm());
        claim.put(BIZ_NO, tokenReq.getBizNo());
        claim.put(JWT_ID, UUID.randomUUID().toString());
        return makeJwt(subject, expiration, claim);
    }

    /**
     * JWT 생성
     *
     * @param subject    주체
     * @param expiration 만료시간
     * @param claim      사용자 정보
     * @return JWT 문자열 생성
     * @throws JwtException JWT 생성 중 오류가 발생
     */
    public String makeJwt(String subject, Long expiration, HashMap<String, Object> claim) {
        try {
            byte[] keyBytes = Decoders.BASE64.decode(secret);
            Key key = Keys.hmacShaKeyFor(keyBytes);

            JwtBuilder jwtBuilder = Jwts.builder()
                    .setHeaderParam("typ", "JWT")
                    .setSubject(subject)
                    .setIssuedAt(new Date())
                    .signWith(key, SignatureAlgorithm.HS512);

            if (claim != null) {
                jwtBuilder.addClaims(claim);
            }

            if (expiration == null || expiration <= 0) {
                expiration = ACCESS_TOKEN_EXPIRATION_TIME;
            }
            jwtBuilder.setExpiration(Date.from(Instant.now().plusMillis(expiration)));

            return jwtBuilder.compact();
        } catch (JwtException e) {
            throw new RuntimeException("JWT 생성 실패" + e.getMessage(), e);
        }
    }

    /**
     * Access Token 유효성 검증
     *
     * @param token 사용자 accessToken
     * @return 토큰 claims
     * @throws JwtException JWT 처리 중 발생 가능한 일반적인 예외
     */
    public Claims validateToken(String token) throws JwtException {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        Key key = Keys.hmacShaKeyFor(keyBytes);

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Refresh Token 유효성 검증
     *
     * @param refreshToken 사용자 refreshToken
     * @return 유효한 refreshToken은 true
     * @throws JwtException JWT 처리 중 발생 가능한 일반적인 예외
     */
    public boolean validateRefreshToken(String refreshToken) {
        try {
            if (!StringUtils.hasText(refreshToken)) {
                return false;
            }

            byte[] keyBytes = Decoders.BASE64.decode(secret);
            Key key = Keys.hmacShaKeyFor(keyBytes);

            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(refreshToken);

            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    /**
     * Access Token 만료 시 재발급
     *
     * @param refreshToken 사용자 refreshToken
     * @return 토큰 claims
     */
    public Claims getNewAccessToken(String refreshToken, HttpServletResponse response) throws IOException {
        refreshToken = extractToken(refreshToken);

        // 새 Access Token 발급
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        Key key = Keys.hmacShaKeyFor(keyBytes);

        Jws<Claims> jws = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(refreshToken);

        Claims claims = jws.getBody();

        LoginVo.TokenReq tokenReq = LoginVo.TokenReq.builder()
                .loginId(claims.get(LOGIN_ID, String.class))
                .membNo(claims.get(MEMB_NO, Long.class))
                .membNm(claims.get(MEMB_NM, String.class))
                .bizNo(claims.get(BIZ_NO, String.class))
                .build();

        String newAccessToken = getUserAccessToken(tokenReq);
        response.setHeader("Authorization", "Bearer " + newAccessToken);

        return validateToken(newAccessToken);
    }

    /**
     * refreshToken을 cookie에서 추출
     */
    public String getRefreshTokenFromCookie(HttpServletRequest request) {
        if (request.getCookies() == null) {
            return null;
        }
        for (Cookie cookie : request.getCookies()) {
            if ("refreshToken".equals(cookie.getName())) {
                return cookie.getValue(); // Refresh Token 반환
            }
        }
        return null;
    }

    /**
     * "Bearer "를 제거한 Token 값을 반환
     */
    public String extractToken(String header) {
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
}
