package com.example.taskmanagementsystems.impl.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.UUID;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;

public class LogoutHandler implements
    org.springframework.security.web.authentication.logout.LogoutHandler {

  @Override
  public void logout(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) {
    String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      authHeader = authHeader.substring(7);
    }
    try {
      UUID username = UUID.fromString(jwtService.extractClaim(authHeader, Claims::getSubject));
      RefreshTokenEntity refreshTokenEntity = refreshTokenService.findByUserId(username);
      refreshTokenService.delete(refreshTokenEntity);
    } catch (SignatureException e) {
      response.setStatus(400);
    }
  }
  }
}
