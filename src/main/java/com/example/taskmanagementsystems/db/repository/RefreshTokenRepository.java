package com.example.taskmanagementsystems.db.repository;

import com.example.taskmanagementsystems.db.entity.RefreshTokenEntity;
import com.example.taskmanagementsystems.db.entity.UserEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, UUID> {

  Optional<RefreshTokenEntity> findByUserEntity(UserEntity userEntity);

//  Optional<RefreshTokenEntity> findRefreshTokenEntityBy(String token);
}
