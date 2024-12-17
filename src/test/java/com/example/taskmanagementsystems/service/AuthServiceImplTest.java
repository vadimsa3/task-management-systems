package com.example.taskmanagementsystems.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.taskmanagementsystems.api.dto.request.AuthorizationRequestDto;
import com.example.taskmanagementsystems.api.dto.response.MessageResponseDto;
import com.example.taskmanagementsystems.db.entity.UserEntity;
import com.example.taskmanagementsystems.db.repository.UserRepository;
import com.example.taskmanagementsystems.impl.exception.AppException;
import com.example.taskmanagementsystems.impl.exception.EnumException;
import com.example.taskmanagementsystems.impl.security.jwt.RefreshTokenService;
import com.example.taskmanagementsystems.impl.service.impl.AuthServiceImpl;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

  @InjectMocks
  private AuthServiceImpl authService;

  @Mock
  private UserRepository userRepository;

  @Mock
  private AuthenticationManager authenticationManager;

  @Mock
  private RefreshTokenService refreshTokenService;

  private UserEntity userEntity;

  @BeforeEach
  void setUp() {
    userEntity = new UserEntity();
    userEntity.setEmail("test@example.com");
    userEntity.setPassword("password");
  }

  @Test
  void authorizeUser_Success() {

    AuthorizationRequestDto requestDto = new AuthorizationRequestDto("test@mail.ru",
        "password");

    when(userRepository.findByEmail(any())).thenReturn(Optional.of(userEntity));
    when(authenticationManager.authenticate(any())).thenReturn(
        new UsernamePasswordAuthenticationToken(userEntity.getEmail(),
            userEntity.getPassword()));
    when(refreshTokenService.refreshAuthToken(userEntity)).thenReturn("mockedToken");

    var response = authService.authorizeUser(requestDto);

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertEquals(new MessageResponseDto("Success"), response.getBody());
    Assertions.assertEquals("Bearer mockedToken",
        response.getHeaders().getFirst(HttpHeaders.AUTHORIZATION));

  }

  @Test
  void givenInvalidPassword_whenTryAuth_thenUnauthorized() {
    AuthorizationRequestDto authorizationRequestDto = new AuthorizationRequestDto("test@mail.ru",
        "wrong_password");

    when(userRepository.findByEmail(any())).thenReturn(Optional.of(userEntity));
    when(authenticationManager.authenticate(any())).thenThrow(BadCredentialsException.class);

    AppException appException = Assertions.assertThrows(AppException.class,
        () -> authService.authorizeUser(authorizationRequestDto));

    Assertions.assertEquals(EnumException.UNAUTHORIZED, appException.getEnumInterface());
    Assertions.assertEquals("Incorrect password", appException.getErrorMessage());
  }

  @Test
  void givenNonExistingEmail_whenTryAuth_thenNotFound() {
    AuthorizationRequestDto authorizationRequestDto =
        new AuthorizationRequestDto("wrong_test@mail.ru", "password");

    when(userRepository.findByEmail(any())).thenReturn(Optional.empty());

    AppException appException = Assertions.assertThrows(AppException.class,
        () -> authService.authorizeUser(authorizationRequestDto));

    Assertions.assertEquals(EnumException.NOT_FOUND, appException.getEnumInterface());
    Assertions.assertEquals("User with email wrong_test@mail.ru not found",
        appException.getErrorMessage());
  }

}


