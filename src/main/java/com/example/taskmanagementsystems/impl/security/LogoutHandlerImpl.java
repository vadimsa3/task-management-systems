package com.example.taskmanagementsystems.impl.security;

import com.example.taskmanagementsystems.db.entity.RefreshTokenEntity;
import com.example.taskmanagementsystems.impl.security.jwt.JwtTokenService;
import com.example.taskmanagementsystems.impl.security.jwt.RefreshTokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Service
@RequiredArgsConstructor
public class LogoutHandlerImpl implements LogoutHandler {

  private final JwtTokenService jwtTokenService;

  private final RefreshTokenService refreshTokenService;

  @Override
  public void logout(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) {

    String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      authHeader = authHeader.substring(7);
    }
    try {
      UUID username = UUID.fromString(jwtTokenService.extractClaim(authHeader, Claims::getSubject));
      RefreshTokenEntity refreshTokenEntity = refreshTokenService.findByUserId(username);
      refreshTokenService.deleteToken(refreshTokenEntity);
    } catch (SignatureException e) {
      response.setStatus(400);
    }
  }

}
