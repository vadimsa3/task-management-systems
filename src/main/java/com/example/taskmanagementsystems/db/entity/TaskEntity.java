package com.example.taskmanagementsystems.db.entity;

import com.example.taskmanagementsystems.db.enums.TaskPriorityEnum;
import com.example.taskmanagementsystems.db.enums.TaskStatusEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tasks")
public class TaskEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "task_id")
  private UUID taskId;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String description;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private TaskStatusEnum status;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private TaskPriorityEnum priority;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "author_id")
  private UserEntity author;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "executor_id")
  private UserEntity executor;

  private String comment;

  private LocalDateTime taskCreateDate;

}
