package com.example.taskmanagementsystems.impl.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
    info = @Info(
        title = "Task-management-system",
        description = "Документация для сервиса Task-management-system",
        version = "1.0.0",
        contact = @Contact(
            name = "Vadim Savchuk",
            email = "vadimsa3@mai.rum"
        )
    )
)

@SecurityScheme(
    name = "JWT Bearer",
    description = "JWT token in Header",
    scheme = "bearer",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    in = SecuritySchemeIn.HEADER
)

@Configuration
public class SwaggerConfig {

}
