package com.example.taskmanagementsystems.impl.controller;

import com.example.taskmanagementsystems.api.controller.AuthController;
import com.example.taskmanagementsystems.api.dto.request.AuthorizationRequestDto;
import com.example.taskmanagementsystems.api.dto.request.CheckRegistrationRequestDto;
import com.example.taskmanagementsystems.api.dto.request.RegistrationUserRequestDto;
import com.example.taskmanagementsystems.api.dto.response.CheckRegistrationResponseDto;
import com.example.taskmanagementsystems.api.dto.response.MessageResponseDto;
import com.example.taskmanagementsystems.api.dto.response.RegistrationUserResponseDto;
import com.example.taskmanagementsystems.impl.security.LogoutHandlerImpl;
import com.example.taskmanagementsystems.impl.service.AuthService;
import com.example.taskmanagementsystems.impl.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthControllerImpl implements AuthController {

  private final AuthService authService;

  @Override
  public ResponseEntity<RegistrationUserResponseDto> registerNewUser(
      RegistrationUserRequestDto registrationUserRequestDto) {
    RegistrationUserResponseDto registrationUserResponseDto = authService.registerNewUser(
        registrationUserRequestDto);
    return new ResponseEntity<>(registrationUserResponseDto, HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<MessageResponseDto> authorizeByEmail(
      AuthorizationRequestDto authorizationRequestDto) {
    return authService.authorizeUser(authorizationRequestDto);
  }

  @Override
  public ResponseEntity<CheckRegistrationResponseDto> checkRegistrationByEmail(
      CheckRegistrationRequestDto checkRegistrationRequestDto) {
    CheckRegistrationResponseDto responseDto = authService.checkRegistration(
        checkRegistrationRequestDto);
    return ResponseEntity.ok(responseDto);
  }

//  @Override
//  public ResponseEntity<MessageResponseDto> logout(UserDetails userDetails) {
//    logoutHandler.logout();
//    return ResponseEntity.ok(new MessageResponseDto("User logout successful. Username is: " +
//        userDetails.getUsername()));
//  }

}
