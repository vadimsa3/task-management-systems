package com.example.taskmanagementsystems.api.controller;

import com.example.taskmanagementsystems.api.annotation.task_contr.CreateTaskOperation;
import com.example.taskmanagementsystems.api.annotation.task_contr.DeleteTaskOperation;
import com.example.taskmanagementsystems.api.annotation.task_contr.GetAllTasksInformationOperation;
import com.example.taskmanagementsystems.api.annotation.task_contr.GetTaskInformationOperation;
import com.example.taskmanagementsystems.api.annotation.task_contr.GetTasksByAuthorIdOperation;
import com.example.taskmanagementsystems.api.annotation.task_contr.GetTasksByExecutorIdOperation;
import com.example.taskmanagementsystems.api.dto.request.RegistrationTaskRequestDto;
import com.example.taskmanagementsystems.api.dto.response.DeleteTaskResponseDto;
import com.example.taskmanagementsystems.api.dto.response.RegistrationTaskResponseDto;
import com.example.taskmanagementsystems.api.dto.response.TaskResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Работа с задачами")
@RequestMapping("/api/task-service")
public interface TaskController {

  @Operation(summary = "Создание новой задачи.",
      description = "Предназначен для создания новой задачи с указанием исполнителя.",
      security = @SecurityRequirement(name = "JWT Bearer"))
  @CreateTaskOperation
  @PostMapping("/create")
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  ResponseEntity<RegistrationTaskResponseDto> createTask(
      @RequestBody RegistrationTaskRequestDto registrationTaskRequestDto);

  @Operation(summary = "Получение информации о текущей задаче.",
      description = "Предназначен для получения полной информации о задаче.",
      security = @SecurityRequirement(name = "JWT Bearer"))
  @GetTaskInformationOperation
  @GetMapping("{taskId}")
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  ResponseEntity<TaskResponseDto> getTaskInformation(
      @PathVariable("taskId") UUID taskId);

  @Operation(summary = "Получение информации о всех задачах.",
      description = "Предназначен для получения полной информации о всех задачах.",
      security = @SecurityRequirement(name = "JWT Bearer"))
  @GetAllTasksInformationOperation
  @GetMapping("/")
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  ResponseEntity<List<TaskResponseDto>> getAllTasksPageable(
      @PathParam("page") int page, @PathParam("limit") int limit);

  @Operation(summary = "Удаление задачи из базы.",
      description = "Удаление задачи из базы осуществляется лицом с правами администратора.",
      security = @SecurityRequirement(name = "JWT Bearer"))
  @DeleteTaskOperation
  @DeleteMapping("/admin/{taskId}")
  @PreAuthorize("hasRole('ADMIN')")
  ResponseEntity<DeleteTaskResponseDto> deleteTask(
      @PathVariable("taskId") UUID taskId);

  @Operation(summary = "Получение информации о всех задачах Автора.",
      description = "Предназначен для получения полной информации о всех задачах по Id "
          + "авторизованного автора.",
      security = @SecurityRequirement(name = "JWT Bearer"))
  @GetTasksByAuthorIdOperation
  @GetMapping("/task-by-author/{authorId}")
  ResponseEntity<List<TaskResponseDto>> getTasksByAuthorId(
      @PathVariable("authorId") UUID authorId);

  @Operation(summary = "Получение информации о всех задачах Исполнителя.",
      description = "Предназначен для получения полной информации о всех задачах по Id "
          + "авторизованного исполнителя.",
      security = @SecurityRequirement(name = "JWT Bearer"))
  @GetTasksByExecutorIdOperation
  @GetMapping("/task-by-author/{executorId}")
  ResponseEntity<List<TaskResponseDto>> getTasksByExecutorId(
      @PathVariable("executorId") UUID executorId);

}