package com.example.taskmanagementsystems.impl.service.impl;

import com.example.taskmanagementsystems.api.dto.request.AuthorizationRequestDto;
import com.example.taskmanagementsystems.api.dto.request.CheckRegistrationRequestDto;
import com.example.taskmanagementsystems.api.dto.request.RegistrationUserRequestDto;
import com.example.taskmanagementsystems.api.dto.response.CheckRegistrationResponseDto;
import com.example.taskmanagementsystems.api.dto.response.MessageResponseDto;
import com.example.taskmanagementsystems.api.dto.response.RegistrationUserResponseDto;
import com.example.taskmanagementsystems.db.entity.UserEntity;
import com.example.taskmanagementsystems.db.enums.RoleEnum;
import com.example.taskmanagementsystems.db.repository.UserRepository;
import com.example.taskmanagementsystems.impl.exception.AppException;
import com.example.taskmanagementsystems.impl.exception.EnumException;
import com.example.taskmanagementsystems.impl.mapper.UserMapper;
import com.example.taskmanagementsystems.impl.security.jwt.RefreshTokenService;
import com.example.taskmanagementsystems.impl.service.AuthService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

  private final UserRepository userRepository;

  private final AuthenticationManager authenticationManager;

  private final RefreshTokenService refreshTokenService;

  private final PasswordEncoder passwordEncoder;

  private final UserMapper userProfileMapper;

  @Override
  public ResponseEntity<MessageResponseDto> authorizeUser(
      AuthorizationRequestDto authorizationRequestDto) {

    String userEmail = authorizationRequestDto.email();
    String userPassword = authorizationRequestDto.password();

    UserEntity userEntity = userRepository.findByEmail(userEmail)
        .orElseThrow(() -> {
          log.error("User with email {} not found", userEmail);
          return new AppException(EnumException.NOT_FOUND,
              String.format("User with email %s not found", userEmail));
        });

    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(userEmail, userPassword));
    } catch (AuthenticationException e) {
      log.error("Authentication failed, incorrect password");
      throw new AppException(EnumException.UNAUTHORIZED, "Incorrect password");

    }
    log.info("User with email {} successfully authenticated", userEmail);
    return ResponseEntity.ok()
        .header(HttpHeaders.AUTHORIZATION, "Bearer " + refreshTokenService
            .refreshAuthToken(userEntity))
        .body(new MessageResponseDto("Success"));
  }

    @Override
    public RegistrationUserResponseDto registerNewUser (
        RegistrationUserRequestDto registrationUserRequestDto){

      if (userRepository.existsByEmail(registrationUserRequestDto.email())) {
        throw new AppException(EnumException.BAD_REQUEST,
            String.format("User with email %s already registered",
                registrationUserRequestDto.email()));
      }
      UserEntity userEntity = userProfileMapper.mapToUserEntity(registrationUserRequestDto);
      userEntity.setUserRegistrationDate(LocalDateTime.now());
      userEntity.setPassword(passwordEncoder.encode(registrationUserRequestDto.password()));
      userEntity.setRole(RoleEnum.USER);
      userRepository.save(userEntity);
      refreshTokenService.createRefreshToken(userEntity);
      log.info("User with email {} created", userEntity.getEmail());
      return userProfileMapper.mapToUserResponseDto(userEntity);
    }

    @Override
    public CheckRegistrationResponseDto checkRegistration (
        CheckRegistrationRequestDto requestDto){

      if (!userRepository.existsByEmail(requestDto.email())) {
        log.info("User with email {} is not exists", requestDto.email());
        return new CheckRegistrationResponseDto(false);
      }

      log.info("User with email {} exists", requestDto.email());
      return new CheckRegistrationResponseDto(true);
    }

//  @Override
//  public ResponseEntity<MessageResponseDto> logout(UserDetails userDetails) {
//    return null;
//  }

  }
