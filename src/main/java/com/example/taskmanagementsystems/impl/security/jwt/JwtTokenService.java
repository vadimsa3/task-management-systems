package com.example.taskmanagementsystems.impl.service.impl;

import com.example.taskmanagementsystems.db.entity.UserEntity;
import com.example.taskmanagementsystems.impl.security.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtTokenService {

  @Value("${app.jwt.secret}")
  private String SECRET_KEY;

  @Value("${app.jwt.tokenExpiration}")
  private int TOKEN_EXPIRATION; // 30 min

  public String generateAuthToken(UserEntity userEntity) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("login", userEntity.getEmail());
    claims.put("authorities", userEntity.getRole());
    return createToken(claims, userEntity);
  }

  private String createToken(Map<String, Object> claims,
      UserEntity userEntity) {
    return Jwts.builder()
        .claims(claims)
        .subject(String.valueOf(userEntity.getUserId()))
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION)) // 30 min
        .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
  }

  private Key getSignKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  public String getUserEmail(String token) {
    return Jwts.parser()
        .setSigningKey(getSignKey())
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
  }

  public Boolean validate(String token) {
    try {
      Jwts.parser().setSigningKey(getSignKey()).build().parseClaimsJws(token);
      return true;
    } catch (SignatureException e) {
      log.error("Invalid signature: {}", e.getMessage());
    } catch (MalformedJwtException e) {
      log.error("Invalid token: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      log.error("Invalid signature: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      log.error("Token is unsupported: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      log.error("Claims string is empty: {}", e.getMessage());
    }
    return false;
  }


}
