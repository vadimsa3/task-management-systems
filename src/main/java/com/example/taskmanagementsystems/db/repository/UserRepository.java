package com.example.taskmanagementsystems.db.repository;

import com.example.taskmanagementsystems.db.entity.UserEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

  Optional<UserEntity> findByUserId(UUID userId);

  Optional<UserEntity> findByEmail(String email);

  boolean existsByEmail(String email);

}
