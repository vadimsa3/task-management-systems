package com.example.taskmanagementsystems.api.dto.request;

import static com.example.taskmanagementsystems.api.validation.constant.ValidationRegex.REGEXP_EMAIL;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Schema(description = "Модель запроса для проверки нахождения пользователя в приложении.")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record CheckRegistrationRequestDto(

    @Schema(description = "Email пользователя", example = "vanya@test.com")
    @Pattern(regexp = REGEXP_EMAIL, message = "Неверный формат адреса электронной почты")
    @NotNull
    String email
) {

}
