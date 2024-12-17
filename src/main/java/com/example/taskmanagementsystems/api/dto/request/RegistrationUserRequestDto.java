package com.example.taskmanagementsystems.api.dto.request;

import static com.example.taskmanagementsystems.api.validation.constant.ValidationRegex.REGEXP_EMAIL;
import static com.example.taskmanagementsystems.api.validation.constant.ValidationRegex.REGEXP_PASSWORD;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Schema(description = "Модель пользователя для регистрации")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record RegistrationUserRequestDto(

    @Schema(description = "Имя пользователя", example = "Иван")
    @Size(max = 50)
    String firstName,

    @Schema(description = "Фамилия пользователя", example = "Иванов")
    @Size(max = 50)
    String lastName,

    @Schema(description = "Email пользователя", example = "vanya@test.com")
    @Pattern(regexp = REGEXP_EMAIL)
    String email,

    @Schema(description = "Пароль пользователя", example = "12345Van")
    @Size(min = 8, max = 50)
    @Pattern(regexp = REGEXP_PASSWORD)
    String password

) {

}
