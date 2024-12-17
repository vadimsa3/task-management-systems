package com.example.taskmanagementsystems.api.dto.request;

import static com.example.taskmanagementsystems.api.validation.constant.ValidationRegex.REGEXP_EMAIL;
import static com.example.taskmanagementsystems.api.validation.constant.ValidationRegex.REGEXP_PASSWORD;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Schema(description = "Модель запроса на авторизацию")
public record AuthorizationRequestDto(

    @Schema(description = "Email пользователя", example = "Test@mail.ru")
    @Pattern(regexp = REGEXP_EMAIL, message = "Неверный формат адреса эдектронной почты")
    @NotNull
    String email,

    @Schema(description = "Пароль пользователя", example = "12345Test")
    @Pattern(regexp = REGEXP_PASSWORD, message = """
        Пароль должен быть от 8 до 50 символов в длину, содержать хотя бы одну заглавную
        букву от A до Z, одну строчную букву от a до z и один из специальных символов
         (~ ! ? @ # $ % ^ & * _ - + ( ) [ ] { } > < / \\ | " ' . , : ;)""")
    @NotNull
    String password
) {

}
