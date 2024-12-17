package com.example.taskmanagementsystems.impl.controller;

import com.example.taskmanagementsystems.api.controller.TaskController;
import com.example.taskmanagementsystems.api.dto.request.RegistrationTaskRequestDto;
import com.example.taskmanagementsystems.api.dto.response.DeleteTaskResponseDto;
import com.example.taskmanagementsystems.api.dto.response.RegistrationTaskResponseDto;
import com.example.taskmanagementsystems.api.dto.response.TaskResponseDto;
import com.example.taskmanagementsystems.impl.service.TaskService;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TaskControllerImpl implements TaskController {

  private final TaskService taskService;

  @Override
  public ResponseEntity<RegistrationTaskResponseDto> createTask(
      RegistrationTaskRequestDto registrationTaskRequestDto) {
    RegistrationTaskResponseDto registrationTaskResponseDto = taskService.createTask(
        registrationTaskRequestDto);
    return new ResponseEntity<>(registrationTaskResponseDto, HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<TaskResponseDto> getTaskInformation(UUID taskId) {
    TaskResponseDto taskResponseDto = taskService.getTask(taskId);
    return ResponseEntity.ok(taskResponseDto);
  }

  @Override
  public ResponseEntity<List<TaskResponseDto>> getAllTasksPageable(int page, int limit) {
    var tasks = taskService.getAllTasks(page, limit);
    return ResponseEntity.ok(tasks);
  }

  @Override
  public ResponseEntity<DeleteTaskResponseDto> deleteTask(UUID taskId) {
    DeleteTaskResponseDto deleteTaskResponseDto = taskService.deleteTask(taskId);
    return ResponseEntity.ok(deleteTaskResponseDto);
  }

  @Override
  public ResponseEntity<List<TaskResponseDto>> getTasksByAuthorId(UUID authorId) {
    var tasks = taskService.getTasksByAuthorId(authorId);
    return ResponseEntity.ok(tasks);
  }

  @Override
  public ResponseEntity<List<TaskResponseDto>> getTasksByExecutorId(UUID executorId) {
    var tasks = taskService.getTasksByExecutorId(executorId);
    return ResponseEntity.ok(tasks);
  }


}
