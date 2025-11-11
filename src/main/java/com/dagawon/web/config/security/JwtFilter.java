package com.dagawon.web.config.security;

import com.dagawon.web.common.userAuth.UserPrincipal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Slf4j
@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public static final String LOGIN_ID = "loginId";
    public static final String MEMB_NO = "membNo";
    public static final String MEMB_NM = "membNm";
    public static final String BIZ_NO = "bizNo";

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        log.debug("**** SECURITY FILTER > {}", request.getRequestURL());

        String token = jwtUtil.extractToken(request.getHeader("Authorization"));

        if (token != null) {
            try {
                // accessToken 검증
                Claims claims = jwtUtil.validateToken(token);
                setAuthenticateUser(claims);
            } catch (ExpiredJwtException e) {
                // accessToken 만료시 refreshToken 검증, 새 accessToken 발급
                String refreshToken = jwtUtil.getRefreshTokenFromCookie(request);

                if (jwtUtil.validateRefreshToken(refreshToken)) {
                    Claims claims = jwtUtil.getNewAccessToken(refreshToken, response);
                    setAuthenticateUser(claims);
                } else {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "만료 된 Refresh Token");
                    return;
                }
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "유효하지 않은 Access Token");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    /**
     * 유효한 Access Token으로 사용자 인증 처리
     */
    private void setAuthenticateUser(Claims claims) {
        String loginId = claims.get(LOGIN_ID, String.class);
        Long membNo = claims.get(MEMB_NO, Long.class);
        String membNm = claims.get(MEMB_NM, String.class);
        Long bizNo = claims.get(BIZ_NO, Long.class);

        UserPrincipal userPrincipal = new UserPrincipal(loginId, membNo, membNm, bizNo);

        // SecurityContext에 사용자 정보를 설정
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(userPrincipal, null, new ArrayList<>())
        );
    }
}
