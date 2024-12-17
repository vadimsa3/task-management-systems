package com.example.taskmanagementsystems.impl.service;

import com.example.taskmanagementsystems.api.dto.request.AuthorizationRequestDto;
import com.example.taskmanagementsystems.api.dto.request.CheckRegistrationRequestDto;
import com.example.taskmanagementsystems.api.dto.request.RegistrationUserRequestDto;
import com.example.taskmanagementsystems.api.dto.response.CheckRegistrationResponseDto;
import com.example.taskmanagementsystems.api.dto.response.MessageResponseDto;
import com.example.taskmanagementsystems.api.dto.response.RegistrationUserResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {

  ResponseEntity<MessageResponseDto> authorizeUser(
      AuthorizationRequestDto authorizationRequestDto);

  RegistrationUserResponseDto registerNewUser(
      RegistrationUserRequestDto registrationUserRequestDto);

  CheckRegistrationResponseDto checkRegistration(
      CheckRegistrationRequestDto checkRegistrationRequestDto);

//  ResponseEntity<MessageResponseDto> logout(
//      UserDetails userDetails);

}
