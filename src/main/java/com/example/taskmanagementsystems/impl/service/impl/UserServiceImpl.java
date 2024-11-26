package com.example.taskmanagementsystems.impl;

import com.example.taskmanagementsystems.api.dto.request.RegistrationUserRequestDto;
import com.example.taskmanagementsystems.api.dto.response.DeleteUserResponseDto;
import com.example.taskmanagementsystems.api.dto.response.RegistrationUserResponseDto;
import com.example.taskmanagementsystems.api.dto.response.UserProfileResponseDto;
import com.example.taskmanagementsystems.db.entity.UserEntity;
import com.example.taskmanagementsystems.db.repository.UserRepository;
import com.example.taskmanagementsystems.impl.exception.AppException;
import com.example.taskmanagementsystems.impl.exception.EnumException;
import com.example.taskmanagementsystems.impl.mapper.UserProfileMapper;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private final UserProfileMapper userProfileMapper;

  @Override
  public RegistrationUserResponseDto registerNewUser(
      RegistrationUserRequestDto registrationUserRequestDto) {
    if (userRepository.existsByEmail(registrationUserRequestDto.email())) {
      throw new AppException(EnumException.BAD_REQUEST,
          String.format("User with email %s already registered",
              registrationUserRequestDto.email()));
    }
    UserEntity userEntity = userProfileMapper.mapToUserEntity(registrationUserRequestDto);
    userRepository.save(userEntity);
    return userProfileMapper.mapToUserResponseDto(userEntity);
  }

  @Override
  public UserProfileResponseDto getUserProfile(UUID userId) {
    UserEntity userEntity = userRepository.findByUserId(userId)
        .orElseThrow(() -> new AppException(
            EnumException.BAD_REQUEST,
            String.format("User with ID %s not found", userId)));
    return userProfileMapper.mapToUserProfileResponseDto(userEntity);
  }

  @Override
  public List<UserProfileResponseDto> getAllUsers(int page, int size) {
    List<UserEntity> userEntities;
    userEntities = userRepository.findAll(PageRequest.of(page, size)).getContent();
    if (userEntities.isEmpty()) {
      throw new AppException(EnumException.NOT_FOUND, HttpStatus.NOT_FOUND, "Users not found");
    }
    return userProfileMapper.toUserResponseList(userEntities);
  }

  @Override
  public DeleteUserResponseDto deleteUser(UUID userId) {
    UserEntity userEntity = userRepository.findByUserId(userId)
        .orElseThrow(() -> new AppException(
            EnumException.BAD_REQUEST,
            String.format("User with ID %s not found", userId)));
    userRepository.delete(userEntity);
    return userProfileMapper.mapToDeleteUserResponseDto(userEntity);
  }

}
