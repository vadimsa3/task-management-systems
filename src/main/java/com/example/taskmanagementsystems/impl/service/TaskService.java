package com.example.taskmanagementsystems.impl.service;

import com.example.taskmanagementsystems.api.dto.request.RegistrationTaskRequestDto;
import com.example.taskmanagementsystems.api.dto.response.DeleteTaskResponseDto;
import com.example.taskmanagementsystems.api.dto.response.RegistrationTaskResponseDto;
import com.example.taskmanagementsystems.api.dto.response.TaskResponseDto;
import java.util.List;
import java.util.UUID;

public interface TaskService {

  RegistrationTaskResponseDto createTask(
      RegistrationTaskRequestDto registrationTaskRequestDto);

  TaskResponseDto getTask(UUID taskId);

  List<TaskResponseDto> getAllTasks(int page, int size);

  DeleteTaskResponseDto deleteTask(UUID taskId);

  List<TaskResponseDto> getTasksByAuthorId(UUID authorId);

  List<TaskResponseDto> getTasksByExecutorId(UUID authorId);

}
