package com.example.taskmanagementsystems.impl;

import com.example.taskmanagementsystems.api.dto.request.RegistrationUserRequestDto;
import com.example.taskmanagementsystems.api.dto.response.DeleteUserResponseDto;
import com.example.taskmanagementsystems.api.dto.response.RegistrationUserResponseDto;
import com.example.taskmanagementsystems.api.dto.response.UserProfileResponseDto;
import java.util.List;
import java.util.UUID;

public interface UserService {

  RegistrationUserResponseDto registerNewUser(
      RegistrationUserRequestDto registrationUserRequestDto);


  UserProfileResponseDto getUserProfile(UUID userId);


  List<UserProfileResponseDto> getAllUsers(int page, int size);


  DeleteUserResponseDto deleteUser(UUID userId);

}
