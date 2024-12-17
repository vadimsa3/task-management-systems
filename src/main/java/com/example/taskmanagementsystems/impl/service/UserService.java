package com.example.taskmanagementsystems.impl.service;

import com.example.taskmanagementsystems.api.dto.response.DeleteUserResponseDto;
import com.example.taskmanagementsystems.api.dto.response.MessageResponseDto;
import com.example.taskmanagementsystems.api.dto.response.UserProfileResponseDto;
import java.util.List;
import java.util.UUID;

public interface UserService {

  UserProfileResponseDto getUserProfile(UUID userId);

  List<UserProfileResponseDto> getAllUsers(int page, int size);

  DeleteUserResponseDto deleteUser(UUID userId);

  MessageResponseDto changeUserRole(UUID userId, String secretKey);
}
