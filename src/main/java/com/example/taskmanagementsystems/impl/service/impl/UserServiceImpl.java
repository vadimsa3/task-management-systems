package com.example.taskmanagementsystems.impl.service.impl;

import com.example.taskmanagementsystems.api.dto.response.DeleteUserResponseDto;
import com.example.taskmanagementsystems.api.dto.response.MessageResponseDto;
import com.example.taskmanagementsystems.api.dto.response.UserProfileResponseDto;
import com.example.taskmanagementsystems.db.entity.UserEntity;
import com.example.taskmanagementsystems.db.enums.RoleEnum;
import com.example.taskmanagementsystems.db.repository.UserRepository;
import com.example.taskmanagementsystems.impl.exception.AppException;
import com.example.taskmanagementsystems.impl.exception.EnumException;
import com.example.taskmanagementsystems.impl.mapper.UserMapper;
import com.example.taskmanagementsystems.impl.service.UserService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private final UserMapper userProfileMapper;

  @Override
  public UserProfileResponseDto getUserProfile(UUID userId) {
    UserEntity userEntity = findUserByIdInRepository(userId);
    log.info("User with id {} founded", userEntity.getUserId());
    return userProfileMapper.mapToUserProfileResponseDto(userEntity);
  }

  @Override
  public List<UserProfileResponseDto> getAllUsers(int page, int size) {
    List<UserEntity> userEntities;
    userEntities = userRepository.findAll(PageRequest.of(page, size)).getContent();
    if (userEntities.isEmpty()) {
      throw new AppException(EnumException.NOT_FOUND, HttpStatus.NOT_FOUND, "Users not found");
    }
    log.info("Users from DB founded");
    return userProfileMapper.toUserResponseList(userEntities);
  }

  @Override
  public DeleteUserResponseDto deleteUser(UUID userId) {
    UserEntity userEntity = findUserByIdInRepository(userId);
    userRepository.delete(userEntity);
    log.info("User with id {} deleted", userEntity.getUserId());
    return userProfileMapper.mapToDeleteUserResponseDto(userEntity);
  }

  @Override
  public MessageResponseDto changeUserRole(UUID userId, String secretKey) {
    String keyForUpdate = "dXNlciB3aXRoIGdvZCByaWdodHMgYW5kIHNlY3JldCBrZXk=";
    if (!secretKey.equals(keyForUpdate)) {
      throw new AppException(EnumException.BAD_REQUEST, "Incorrect secret key.");
    }
    log.warn("The user with {} has no god's rights", userId);
    UserEntity userEntity = findUserByIdInRepository(userId);
    userEntity.setRole(RoleEnum.ADMIN);
    userRepository.save(userEntity);
    log.info("User with id {} role successfully changed to ADMIN", userEntity.getUserId());
    return new MessageResponseDto("Congratulations! You an ADMIN now.");
  }

  private UserEntity findUserByIdInRepository(UUID userId) {
    return userRepository.findByUserId(userId)
        .orElseThrow(() -> new AppException(
            EnumException.BAD_REQUEST,
            String.format("User with ID %s not found", userId)));
  }

}

