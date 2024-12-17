package com.example.taskmanagementsystems.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.taskmanagementsystems.api.controller.AuthController;
import com.example.taskmanagementsystems.api.dto.request.AuthorizationRequestDto;
import com.example.taskmanagementsystems.api.dto.request.CheckRegistrationRequestDto;
import com.example.taskmanagementsystems.api.dto.request.RegistrationUserRequestDto;
import com.example.taskmanagementsystems.api.dto.response.CheckRegistrationResponseDto;
import com.example.taskmanagementsystems.api.dto.response.MessageResponseDto;
import com.example.taskmanagementsystems.api.dto.response.RegistrationUserResponseDto;
import com.example.taskmanagementsystems.impl.controller.AuthControllerImpl;
import com.example.taskmanagementsystems.impl.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hamcrest.core.StringContains;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

  @Mock
  AuthService authService;

  AuthController authController;

  private MockMvc mockMvc;

  private ObjectMapper objectMapper;

  @BeforeEach
  public void init() {
    authController = new AuthControllerImpl(authService);
    mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    objectMapper = new ObjectMapper();
  }

  @Test
  void registerNewUserTest() throws Exception {
    UUID userId = UUID.randomUUID();
    LocalDateTime userRegistrationDate = LocalDateTime.now();

    RegistrationUserRequestDto registrationUserRequestDto = new RegistrationUserRequestDto(
        "Test", "Test", "Test", "123Test");

    RegistrationUserResponseDto responseDto = new RegistrationUserResponseDto(userId,
        userRegistrationDate);

    when(authService.registerNewUser(any())).thenReturn(responseDto);

    mockMvc.perform(post("/api/user-service/registration/user")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(registrationUserRequestDto)))
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    Assertions.assertDoesNotThrow(() -> authController.registerNewUser(registrationUserRequestDto));
  }

  @Test
  void authorizeByEmailTest_Success() {
    String token = "Bearer " + UUID.randomUUID();

    AuthorizationRequestDto requestDto = new AuthorizationRequestDto("test@mail.ru",
        "password");

    MessageResponseDto responseDto = new MessageResponseDto("Success");

    when(authService.authorizeUser(requestDto)).thenReturn(
        ResponseEntity.status(HttpStatus.OK).header("Authorization", token)
            .body(new MessageResponseDto("Success")));

    var response = authController.authorizeByEmail(requestDto);
    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertEquals(responseDto, response.getBody());
    Assertions.assertEquals(token, response.getHeaders().get("Authorization").get(0));
    Assertions.assertDoesNotThrow(() -> authController.authorizeByEmail(requestDto));
  }

  @Test
  void checkRegistrationByEmailTest() throws Exception {
    CheckRegistrationRequestDto requestDto = new CheckRegistrationRequestDto("test@example.com");
    CheckRegistrationResponseDto responseDto = new CheckRegistrationResponseDto(true);

    when(authService.checkRegistration(any())).thenReturn(responseDto);

    mockMvc.perform(post("/api/user-service/user/check")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestDto)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().string(StringContains.containsString("true")));
    Assertions.assertDoesNotThrow(() -> authController.checkRegistrationByEmail(requestDto));
  }

}
