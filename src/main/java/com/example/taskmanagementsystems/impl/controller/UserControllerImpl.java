package com.example.taskmanagementsystems.impl.controller;

import com.example.taskmanagementsystems.api.controller.UserController;
import com.example.taskmanagementsystems.api.dto.response.DeleteUserResponseDto;
import com.example.taskmanagementsystems.api.dto.response.MessageResponseDto;
import com.example.taskmanagementsystems.api.dto.response.UserProfileResponseDto;
import com.example.taskmanagementsystems.impl.service.UserService;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserControllerImpl implements UserController {

  private final UserService userService;

  @Override
  public ResponseEntity<UserProfileResponseDto> getUserInformation(UUID userId) {
    UserProfileResponseDto userProfileResponseDto = userService.getUserProfile(userId);
    return ResponseEntity.ok(userProfileResponseDto);
  }

  @Override
  public ResponseEntity<List<UserProfileResponseDto>> getAllUsersPageable(int page, int limit) {
    var users = userService.getAllUsers(page, limit);
    return ResponseEntity.ok(users);
  }

  @Override
  public ResponseEntity<DeleteUserResponseDto> deleteUser(UUID userId) {
    DeleteUserResponseDto deleteUserResponseDto = userService.deleteUser(userId);
    return ResponseEntity.ok(deleteUserResponseDto);
  }

  @Override
  public ResponseEntity<MessageResponseDto> changeUserRole(UUID userId, String secretKey) {
    MessageResponseDto messageResponseDto = userService.changeUserRole(userId, secretKey);
    return ResponseEntity.ok(messageResponseDto);
  }
}
