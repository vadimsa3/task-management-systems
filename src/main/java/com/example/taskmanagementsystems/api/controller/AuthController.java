package com.example.taskmanagementsystems.api.controller;

import com.example.taskmanagementsystems.api.annotation.auth_contr.CheckRegistrationOperation;
import com.example.taskmanagementsystems.api.annotation.auth_contr.UserAuthorizeOperation;
import com.example.taskmanagementsystems.api.annotation.auth_contr.UserRegistrationOperation;
import com.example.taskmanagementsystems.api.dto.request.AuthorizationRequestDto;
import com.example.taskmanagementsystems.api.dto.request.CheckRegistrationRequestDto;
import com.example.taskmanagementsystems.api.dto.request.RegistrationUserRequestDto;
import com.example.taskmanagementsystems.api.dto.response.CheckRegistrationResponseDto;
import com.example.taskmanagementsystems.api.dto.response.MessageResponseDto;
import com.example.taskmanagementsystems.api.dto.response.RegistrationUserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Авторизация пользователя")
@RequestMapping("/api/user-service")
@Validated
public interface AuthController {

  @Operation(summary = "Регистрация нового пользователя.",
      description = "Предназначен для регистрации нового пользователя в приложении.")
  @UserRegistrationOperation
  @PostMapping("/registration/user")
  ResponseEntity<RegistrationUserResponseDto> registerNewUser(
      @RequestBody RegistrationUserRequestDto registrationUserRequestDto);

  @Operation(summary = "Авторизация пользователя.",
      description = "Предназначен для авторизации пользователя в приложении.",
      security = @SecurityRequirement(name = "JWT Bearer"))
  @UserAuthorizeOperation
  @PostMapping("/auth/login")
  ResponseEntity<MessageResponseDto> authorizeByEmail(
      @RequestBody AuthorizationRequestDto authorizationRequestDto);

  @Operation(summary = "Проверка регистрации пользователя.",
      description = "Предназначен для проверки регистрации пользователя в приложении.",
      security = @SecurityRequirement(name = "JWT Bearer"))
  @CheckRegistrationOperation
  @PostMapping("/user/check")
  ResponseEntity<CheckRegistrationResponseDto> checkRegistrationByEmail(
      @RequestBody CheckRegistrationRequestDto checkRegistrationRequestDto);

}
