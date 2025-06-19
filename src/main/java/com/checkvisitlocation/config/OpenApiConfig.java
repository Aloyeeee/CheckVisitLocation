package com.checkvisitlocation.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфігурація OpenAPI для Swagger UI.
 * Налаштовує документацію API та безпеку для Swagger UI.
 * 
 * @author CheckVisitLocation Team
 * @version 1.0
 * @since 2025
 */
@Configuration
public class OpenApiConfig {
    /**
     * Створює конфігурацію OpenAPI для Swagger UI.
     * Налаштовує інформацію про API, версію, опис та безпеку.
     * 
     * @return налаштований об'єкт OpenAPI
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("CheckVisitLocation API")
                        .version("1.0")
                        .description("API для системи відстеження відвідувань"))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .name("bearerAuth")
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }
}