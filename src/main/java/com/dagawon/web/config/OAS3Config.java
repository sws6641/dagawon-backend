package com.dagawon.web.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import jakarta.servlet.ServletContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Slf4j
@Configuration
public class OAS3Config {
    @Bean
    public OpenAPI customizeOpenAPI(ServletContext servletContext) {

        final String securitySchemeName = "bearer DaGaWonWeb";
        Info info = new Info()
                .version("v1.0.0")
                .title("DaGaWon Project")
                .description("다가원 입니다");

        Server server = new Server().url(servletContext.getContextPath());

        log.debug("########################################################################");
        log.debug("### DaGaWonA-Web-Server OpenAPI30Configuration Configuration");
        log.debug("########################################################################");

        return new OpenAPI()
                .info(info)
                .servers(List.of(server))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(
                        new Components()
                                .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT"))
                );
    }

}
