package com.dagawon.web.config.security;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity /*스프링 시큐리티 활성화 및 웹 됨안 설정 구성하는데 사용*/
public class SecurityConfig {

    @Value("${spring.application.name}")
    private String appName;

    private final JwtFilter jwtFilter;

    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    private final String[] SWAGGER = {
            "/api-docs"                    , "/swagger-ui/**", "/api/api/api-docs" ,   "/api/api/api-docs/swagger-config",
            "/api-docs/swagger-config"     , "/api-docs.yaml", "/api/api-docs" ,
            "/swagger-resources/**"        , "/configuration/security",
            "/webjars/**"                  , "/swagger-ui.html"
    };
    private final String[] UI = {
            "/css/**"                      , "/img/**",
            "/static/**"                   , "/resources/**",
            "/comm/code/**"                , "/comm/**",
            "/common/**"                   , "/auth/login"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.debug("#########    ----------- Auth SecurityFilterChain --------------  ######");
        log.debug("#########    ----------- Application Name : " + appName + " --------------  ######");
        http
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .cors(cors -> cors.configurationSource(request -> {
                    var config = new CorsConfiguration();
                    config.setAllowedOrigins(List.of(
                            "https://localhost:4000",
                            "http://localhost:8071",
                            "https://appkoserdev.kosapp.co.kr",
                            "https://adminkoserdev.kosapp.co.kr",
                            "https://apikoserdev.kosapp.co.kr",
                            "https://app.koser.co.kr",
                            "https://admin.koser.co.kr",
                            "https://api.koser.co.kr"
                    ));
                    config.setAllowedMethods(List.of("GET", "POST", "PATCH", "DELETE"));
                    config.setAllowedHeaders(List.of("*"));
                    config.setAllowCredentials(true);
                    return config;
                }))
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(UI).permitAll()
                        .requestMatchers(SWAGGER).permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(customAuthenticationEntryPoint)
                );

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtExceptionFilter(), JwtFilter.class);

        return http.build();
    }

    @Bean
    public JwtExceptionFilter jwtExceptionFilter() {
        return new JwtExceptionFilter();
    }
}
