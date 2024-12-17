package com.example.taskmanagementsystems.api.controller;

import com.example.taskmanagementsystems.api.annotation.user_contr.ChangeUserRoleOperation;
import com.example.taskmanagementsystems.api.annotation.user_contr.DeleteUserOperation;
import com.example.taskmanagementsystems.api.annotation.user_contr.GetAllUsersInformationOperation;
import com.example.taskmanagementsystems.api.annotation.user_contr.GetUserInformationOperation;
import com.example.taskmanagementsystems.api.dto.response.DeleteUserResponseDto;
import com.example.taskmanagementsystems.api.dto.response.MessageResponseDto;
import com.example.taskmanagementsystems.api.dto.response.UserProfileResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import jakarta.websocket.server.PathParam;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Действия с пользователями")
@RequestMapping("/api/user-service")
@Validated
public interface UserController {

  @Operation(summary = "Получение развернутой информации о пользователе.",
      description = "Предназначен для получения полной информации о пользователе. "
          + "Запрос от зарегистрированного пользователя.",
      security = @SecurityRequirement(name = "JWT Bearer"))
  @GetUserInformationOperation
  @GetMapping("/user/{userId}")
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  ResponseEntity<UserProfileResponseDto> getUserInformation(
      @PathVariable("userId") UUID userId);

  @Operation(summary = "Получение развернутой информации о всех пользователях.",
      description = "Предназначен для получения полной информации о всех пользователях. "
          + "Запрос от зарегистрированного пользователя.",
      security = @SecurityRequirement(name = "JWT Bearer"))
  @GetAllUsersInformationOperation
  @GetMapping("/")
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  ResponseEntity<List<UserProfileResponseDto>> getAllUsersPageable(
      @PathParam("page") int page, @PathParam("limit") int limit);

  @Operation(summary = "Удаление пользователя из базы.",
      description = "Удаление пользователя из базы осуществляется лицом с правами администратора.",
      security = @SecurityRequirement(name = "JWT Bearer"))
  @DeleteUserOperation
  @DeleteMapping("/admin/{userId}")
  @PreAuthorize("hasRole('ADMIN')")
  ResponseEntity<DeleteUserResponseDto> deleteUser(
      @PathVariable("userId") UUID userId);

  // !!! secret key: dXNlciB3aXRoIGdvZCByaWdodHMgYW5kIHNlY3JldCBrZXk=
  @Operation(summary = "Изменение роли пользователя и выдача прав администратора.",
      description = "Наделение пользователя правами администратора. "
          + "Смена роли любого пользователя лицом, обладающим secret key.",
      security = @SecurityRequirement(name = "JWT Bearer"))
  @ChangeUserRoleOperation
  @PutMapping("/user/update/{userId}")
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  ResponseEntity<MessageResponseDto> changeUserRole(
      @PathVariable("userId") UUID userId,
      @RequestHeader("secretKey") @NotNull String secretKey);

}

