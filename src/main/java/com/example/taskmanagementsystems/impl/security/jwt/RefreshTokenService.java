package com.example.taskmanagementsystems.impl.security.jwt;

import com.example.taskmanagementsystems.db.entity.RefreshTokenEntity;
import com.example.taskmanagementsystems.db.entity.UserEntity;
import com.example.taskmanagementsystems.db.repository.RefreshTokenRepository;
import com.example.taskmanagementsystems.impl.exception.AppException;
import com.example.taskmanagementsystems.impl.exception.EnumException;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

  private final RefreshTokenRepository refreshTokenRepository;

  private final JwtTokenService jwtTokenService;

  @Value("${app.jwt.refreshmentExpiration}")
  private int REFRESH_TOKEN_EXPIRATION; // 30 days

  public void createRefreshToken(UserEntity userEntity) {
    RefreshTokenEntity refreshToken = new RefreshTokenEntity();
    refreshToken.setUserEntity(userEntity);
    refreshToken.setTokenId(userEntity.getUserId());
    refreshToken.setIssuedAt(LocalDateTime.now());
    refreshToken.setExpiryAt(LocalDateTime.now().plusDays(30));
    refreshToken.setRefreshToken(UUID.randomUUID().toString());
    refreshTokenRepository.save(refreshToken);
  }

  public String refreshAuthToken(UserEntity userEntity) {
    RefreshTokenEntity refreshToken = refreshTokenRepository.findByUserEntity(userEntity)
        .orElseThrow(() ->
            new AppException(EnumException.UNAUTHORIZED, "No refresh token for user"));
    refreshToken.setExpiryAt(LocalDateTime.now().plusDays(REFRESH_TOKEN_EXPIRATION));
    return jwtTokenService.generateAuthToken(userEntity);
  }

  public RefreshTokenEntity findByUserId(UUID userId) {
    return refreshTokenRepository.findById(userId)
        .orElseThrow(() ->
            new AppException(EnumException.NOT_FOUND, "Token not found"));
  }

  public RefreshTokenEntity checkRefreshToken(RefreshTokenEntity token) {
    if (token.getExpiryAt().compareTo(LocalDateTime.now()) < 0) {
      refreshTokenRepository.delete(token);
      throw new AppException(EnumException.UNAUTHORIZED, "Token expired");
    }
    return token;
  }

//  public Optional<RefreshTokenEntity> findByToken(String token) {
//    return refreshTokenRepository.findRefreshTokenEntityBy(token);
//  }

  public void deleteToken(RefreshTokenEntity refreshToken) {
    refreshTokenRepository.delete(refreshToken);
  }

}
