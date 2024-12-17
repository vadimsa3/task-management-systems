package com.example.taskmanagementsystems.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.taskmanagementsystems.api.dto.response.DeleteUserResponseDto;
import com.example.taskmanagementsystems.api.dto.response.MessageResponseDto;
import com.example.taskmanagementsystems.api.dto.response.UserProfileResponseDto;
import com.example.taskmanagementsystems.db.entity.UserEntity;
import com.example.taskmanagementsystems.db.enums.RoleEnum;
import com.example.taskmanagementsystems.db.repository.UserRepository;
import com.example.taskmanagementsystems.impl.exception.AppException;
import com.example.taskmanagementsystems.impl.exception.EnumException;
import com.example.taskmanagementsystems.impl.mapper.UserMapper;
import com.example.taskmanagementsystems.impl.service.impl.UserServiceImpl;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class UserServiceImplTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private UserMapper userMapper;

  @InjectMocks
  private UserServiceImpl userService;

  private UserEntity userEntity;

  private UUID userId;

  @BeforeEach
  void setUp() {
    userId = UUID.randomUUID();
    userEntity = new UserEntity();
    userEntity.setUserId(userId);
    userEntity.setEmail("test@example.com");
    userEntity.setPassword("password");
    userEntity.setUserRegistrationDate(LocalDateTime.now());
    userEntity.setRole(RoleEnum.USER);
  }

  @Test
  void getUserProfileTest_Success() {

    UserProfileResponseDto userProfileResponseDto =
        new UserProfileResponseDto(userId, "Test", "Test",
            "test@mail.ru", userEntity.getUserRegistrationDate());

    when(userRepository.findByUserId(userId)).thenReturn(Optional.of(userEntity));
    when(userMapper.mapToUserProfileResponseDto(userEntity)).thenReturn(userProfileResponseDto);

    var response = userService.getUserProfile(userId);

    assertNotNull(response);
    assertEquals(userId, response.userId());
    assertEquals("test@mail.ru", response.email());
  }

  @Test
  void getUserProfileTest_UserNotFound() {

    UUID incorrectUserId = UUID.fromString("0e09e13f-e17e-4f9d-9da6-1e1edaaab5e1");

    when(userRepository.findByUserId(incorrectUserId)).thenReturn(Optional.empty());

    AppException exception = assertThrows(
        AppException.class, () -> userService.getUserProfile(incorrectUserId));

    assertEquals(EnumException.BAD_REQUEST, exception.getEnumInterface());
    assertEquals("User with ID 0e09e13f-e17e-4f9d-9da6-1e1edaaab5e1 not found",
        exception.getErrorMessage());
  }

  @Test
  void testGetAllUsers_Success() {
    int page = 0;
    int size = 10;

    List<UserEntity> userEntities =
        Arrays.asList(userEntity, userEntity, userEntity);

    when(userRepository.findAll(PageRequest.of(page, size))).thenReturn(
        new PageImpl<>(userEntities));

    when(userMapper.toUserResponseList(userEntities)).thenReturn(
        Collections.singletonList(new UserProfileResponseDto(
            UUID.randomUUID(), "Test", "Test",
            "test@mail.ru", LocalDateTime.now())));

    List<UserProfileResponseDto> result = userService.getAllUsers(page, size);

    assertFalse(result.isEmpty());

  }

  @Test
  void getAllUsersTest_NotFoundUsers() {
    int page = 0;
    int size = 10;

    when(userRepository.findAll(PageRequest.of(page, size))).thenReturn(
        new PageImpl<>(Collections.emptyList()));

    AppException exception = assertThrows(AppException.class,
        () -> userService.getAllUsers(page, size));

    assertEquals(EnumException.NOT_FOUND, exception.getEnumInterface());
  }

  @Test
  void deleteUserTest_Success() {

    when(userRepository.findByUserId(userId)).thenReturn(Optional.of(userEntity));

    DeleteUserResponseDto responseDto = new DeleteUserResponseDto(userId, "test@example.com");

    when(userMapper.mapToDeleteUserResponseDto(userEntity)).thenReturn(responseDto);

    DeleteUserResponseDto result = userService.deleteUser(userId);

    verify(userRepository).delete(userEntity);
    assertNotNull(result);
  }

  @Test
  void deleteUserTest_UserNotFound() {

    when(userRepository.findByUserId(userId)).thenReturn(Optional.empty());

    AppException exception = assertThrows(AppException.class, () -> userService.deleteUser(userId));

    assertEquals(EnumException.BAD_REQUEST, exception.getEnumInterface());
  }

  @Test
  void changeUserRoleTest_Success() {

    String secretKey = "dXNlciB3aXRoIGdvZCByaWdodHMgYW5kIHNlY3JldCBrZXk=";

    when(userRepository.findByUserId(userId)).thenReturn(Optional.of(userEntity));

    MessageResponseDto responseDto = new MessageResponseDto("Congratulations! You an ADMIN now.");

    MessageResponseDto result = userService.changeUserRole(userId, secretKey);

    assertEquals(responseDto.message(), result.message());
  }

  @Test
  void changeUserRoleTest_IncorrectSecretKey() {

    String secretKey = "wrongSecretKey";

    AppException exception = assertThrows(AppException.class,
        () -> userService.changeUserRole(userId, secretKey));

    assertEquals(EnumException.BAD_REQUEST, exception.getEnumInterface());
  }

}
