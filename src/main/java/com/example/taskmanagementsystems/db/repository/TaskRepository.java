package com.example.taskmanagementsystems.db.repository;

import com.example.taskmanagementsystems.db.entity.TaskEntity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, UUID> {

  Optional<TaskEntity> findByTaskId(UUID taskId);

  List<TaskEntity> findTaskEntitiesByAuthorUserId(UUID authorId);

  List<TaskEntity> findTaskEntitiesByExecutorUserId(UUID authorId);

}
