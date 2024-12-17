package com.example.taskmanagementsystems.impl.service.impl;

import com.example.taskmanagementsystems.api.dto.request.RegistrationTaskRequestDto;
import com.example.taskmanagementsystems.api.dto.response.DeleteTaskResponseDto;
import com.example.taskmanagementsystems.api.dto.response.RegistrationTaskResponseDto;
import com.example.taskmanagementsystems.api.dto.response.TaskResponseDto;
import com.example.taskmanagementsystems.db.entity.TaskEntity;
import com.example.taskmanagementsystems.db.entity.UserEntity;
import com.example.taskmanagementsystems.db.repository.TaskRepository;
import com.example.taskmanagementsystems.db.repository.UserRepository;
import com.example.taskmanagementsystems.impl.exception.AppException;
import com.example.taskmanagementsystems.impl.exception.EnumException;
import com.example.taskmanagementsystems.impl.mapper.TaskMapper;
import com.example.taskmanagementsystems.impl.service.TaskService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

  private final TaskRepository taskRepository;

  private final TaskMapper taskMapper;

  private final UserRepository userRepository;

  @Override
  public RegistrationTaskResponseDto createTask(
      RegistrationTaskRequestDto registrationTaskRequestDto) {
    String authorEmail = registrationTaskRequestDto.authorEmail();
    String executorEmail = registrationTaskRequestDto.executorEmail();
    UserEntity author = userRepository.findByEmail(authorEmail)
        .orElseThrow(() -> new AppException(
            EnumException.BAD_REQUEST,
            String.format("Author with email %s not found", authorEmail)));
    UserEntity executor = userRepository.findByEmail(executorEmail)
        .orElseThrow(() -> new AppException(
            EnumException.BAD_REQUEST,
            String.format("Executor with email %s not found", executorEmail)));
    TaskEntity taskEntity = taskMapper.mapToTaskEntity(registrationTaskRequestDto);
    taskEntity.setAuthor(author);
    taskEntity.setExecutor(executor);
    taskEntity.setTaskCreateDate(LocalDateTime.now());
    taskRepository.save(taskEntity);
    log.info("Task with id {} created", taskEntity.getTaskId());
    return taskMapper.mapToRegistrationTaskResponseDto(taskEntity);
  }

  @Override
  public TaskResponseDto getTask(UUID taskId) {
    TaskEntity taskEntity = taskRepository.findByTaskId(taskId)
        .orElseThrow(() -> new AppException(
            EnumException.BAD_REQUEST,
            String.format("Task with ID %s not found", taskId)));
    log.info("Task with id {} founded", taskId);
    return taskMapper.mapToTaskDto(taskEntity);
  }

  @Override
  public List<TaskResponseDto> getAllTasks(int page, int size) {
    List<TaskEntity> taskEntities;
    taskEntities = taskRepository.findAll(PageRequest.of(page, size)).getContent();
    if (taskEntities.isEmpty()) {
      throw new AppException(EnumException.NOT_FOUND, HttpStatus.NOT_FOUND, "Tasks not found");
    }
    log.info("Tasks founded");
    return taskMapper.toTaskResponseList(taskEntities);
  }

  @Override
  public DeleteTaskResponseDto deleteTask(UUID taskId) {
    TaskEntity taskEntity = taskRepository.findByTaskId(taskId)
        .orElseThrow(() -> new AppException(
            EnumException.BAD_REQUEST,
            String.format("Task with ID %s not found", taskId)));
    taskRepository.delete(taskEntity);
    log.info("Task with id {} deleted", taskId);
    return taskMapper.mapToDeleteTaskResponseDto(taskEntity);
  }

  @Override
  public List<TaskResponseDto> getTasksByAuthorId(UUID authorId) {
    List<TaskEntity> tasks;
    tasks = taskRepository.findTaskEntitiesByAuthorUserId(authorId);
    if (tasks.isEmpty()) {
      throw new AppException(
          EnumException.BAD_REQUEST,
          String.format("Tasks with AuthorID %s not found", authorId));
    }
    return taskMapper.toTaskResponseList(tasks);
  }

  @Override
  public List<TaskResponseDto> getTasksByExecutorId(UUID executorId) {
    List<TaskEntity> tasks;
    tasks = taskRepository.findTaskEntitiesByExecutorUserId(executorId);
    if (tasks.isEmpty()) {
      throw new AppException(
          EnumException.BAD_REQUEST,
          String.format("Task with ExecutorID %s not found", executorId));
    }
    return taskMapper.toTaskResponseList(tasks);
  }

}

