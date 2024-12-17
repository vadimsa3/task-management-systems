package com.example.taskmanagementsystems.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "refresh_token")
public class RefreshTokenEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "token_id")
  private UUID tokenId;

  @Column(name = "refresh_token")
  private String refreshToken;

  @Column(name = "issued_time")
  private LocalDateTime issuedAt;

  @Column(name = "expiry_time")
  private LocalDateTime expiryAt;

  @OneToOne
  @JoinColumn(name = "user_id")
  private UserEntity userEntity;

}
