package com.example.taskmanagementsystems.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.taskmanagementsystems.api.dto.response.DeleteUserResponseDto;
import com.example.taskmanagementsystems.api.dto.response.MessageResponseDto;
import com.example.taskmanagementsystems.api.dto.response.UserProfileResponseDto;
import com.example.taskmanagementsystems.impl.controller.UserControllerImpl;
import com.example.taskmanagementsystems.impl.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class UserControllerTest {

  @InjectMocks
  private UserControllerImpl userController;

  @Mock
  private UserServiceImpl userService;

  private MockMvc mockMvc;

  private ObjectMapper mapperWithSupportJacksonDateAndTimeTypes;

  private UUID userId;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    userId = UUID.randomUUID();
    mapperWithSupportJacksonDateAndTimeTypes = new ObjectMapper().registerModule(
        new JavaTimeModule());
  }

  @Test
  @WithMockUser(roles = {"ADMIN"})
  void getUserInformationTest() throws Exception {

    LocalDateTime userRegistrationDate = LocalDateTime.now();

    UserProfileResponseDto userProfileResponseDto =
        new UserProfileResponseDto(userId, "Test", "Test", "test@mail.ru",
            userRegistrationDate);

    when(userService.getUserProfile(userId)).thenReturn(userProfileResponseDto);

    mockMvc.perform(get("/api/user-service/user/{userId}", userId)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(
            mapperWithSupportJacksonDateAndTimeTypes.writeValueAsString(userProfileResponseDto)));

    Assertions.assertDoesNotThrow(() -> userController.getUserInformation(userId));

  }

  @Test
  @WithMockUser(roles = {"ADMIN"})
  void getAllUsersPageableTest() throws Exception {
    int page = 0;
    int limit = 10;

    List<UserProfileResponseDto> allUsers = Collections.singletonList(new UserProfileResponseDto(
        UUID.randomUUID(), "Test", "Test",
        "test@mail.ru", LocalDateTime.now()));

    when(userService.getAllUsers(page, limit)).thenReturn(allUsers);

    mockMvc.perform(get("/api/user-service/")
            .param("page", String.valueOf(page))
            .param("limit", String.valueOf(limit))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(
            mapperWithSupportJacksonDateAndTimeTypes.writeValueAsString(allUsers)));

    Assertions.assertDoesNotThrow(() -> userController.getUserInformation(userId));

    verify(userService, times(1)).getAllUsers(page, limit);
  }

  @Test
  @WithMockUser(roles = {"ADMIN"})
  void deleteUserTest() throws Exception {

    DeleteUserResponseDto responseDto = new DeleteUserResponseDto(
        userId, "test@mail.ru");

    when(userService.deleteUser(userId)).thenReturn(responseDto);

    mockMvc.perform(delete("/api/user-service/admin/{userId}", userId)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(
            mapperWithSupportJacksonDateAndTimeTypes.writeValueAsString(responseDto)));

    verify(userService, times(1)).deleteUser(userId);
  }

  @Test
  void changeUserRoleTest() throws Exception {

    String secretKey = "dXNlciB3aXRoIGdvZCByaWdodHMgYW5kIHNlY3JldCBrZXk=";

    MessageResponseDto messageResponseDto = new MessageResponseDto(
        "Congratulations! You an ADMIN now.");

    when(userService.changeUserRole(userId, secretKey)).thenReturn(messageResponseDto);

    mockMvc.perform(put("/api/user-service/user/update/{userId}", userId)
            .header("secretKey", secretKey)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(
            mapperWithSupportJacksonDateAndTimeTypes.writeValueAsString(messageResponseDto)));

    verify(userService, times(1)).changeUserRole(userId,
        secretKey);
  }


}
