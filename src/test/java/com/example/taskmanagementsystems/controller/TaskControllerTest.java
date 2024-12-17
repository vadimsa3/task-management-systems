package com.example.taskmanagementsystems.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.taskmanagementsystems.api.controller.TaskController;
import com.example.taskmanagementsystems.api.dto.request.RegistrationTaskRequestDto;
import com.example.taskmanagementsystems.api.dto.response.DeleteTaskResponseDto;
import com.example.taskmanagementsystems.api.dto.response.RegistrationTaskResponseDto;
import com.example.taskmanagementsystems.api.dto.response.TaskResponseDto;
import com.example.taskmanagementsystems.impl.controller.TaskControllerImpl;
import com.example.taskmanagementsystems.impl.service.TaskService;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

  @Mock
  private TaskService taskService;

  private TaskController taskController;

  @BeforeEach
  public void setUp() {
    taskController = new TaskControllerImpl(taskService);
  }

  @Test
  void createTaskTest() {

    UUID taskId = UUID.fromString("014366cb-c3d7-42bb-af0d-d5f9d6937bf7");
    LocalDateTime taskDateTime = LocalDateTime.now();

    RegistrationTaskRequestDto requestDto = new RegistrationTaskRequestDto(
        "Test", "Test", "PENDING", "HIGH", "test1@mail.ru",
        "test2@mail.ru", "Test");

    RegistrationTaskResponseDto responseDto = new RegistrationTaskResponseDto(
        taskId, taskDateTime);

    when(taskService.createTask(any())).thenReturn(responseDto);

    var response = taskController.createTask(requestDto);
    Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    Assertions.assertEquals(responseDto, response.getBody());
    verify(taskService, times(1)).createTask(requestDto);
  }

  @Test
  void getTaskInformationTest() {
    UUID taskId = UUID.fromString("014366cb-c3d7-42bb-af0d-d5f9d6937bf7");
    UUID authorId = UUID.fromString("d13d2c9e-698b-9873-ae96-8436d9ca211d");
    UUID executorId = UUID.fromString("1cbfce9a-fa90-4598-a601-c327d793f3e5");
    LocalDateTime taskDateTime = LocalDateTime.now();

    TaskResponseDto taskResponseDto = new TaskResponseDto(
        taskId, "Test", "Test", "PENDING", "HIGH", authorId,
        executorId, "Test", taskDateTime);

    when(taskService.getTask(taskId)).thenReturn(taskResponseDto);

    var response = taskController.getTaskInformation(taskId);
    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertEquals(taskResponseDto, response.getBody());
    verify(taskService, times(1)).getTask(taskId);
  }

  @Test
  void getAllTasksPageableTest() {
    int page = 0;
    int limit = 10;
    UUID taskId = UUID.fromString("014366cb-c3d7-42bb-af0d-d5f9d6937bf7");
    UUID authorId = UUID.fromString("d13d2c9e-698b-9873-ae96-8436d9ca211d");
    UUID executorId = UUID.fromString("1cbfce9a-fa90-4598-a601-c327d793f3e5");
    LocalDateTime taskDateTime = LocalDateTime.now();
    List<TaskResponseDto> allResponses = Collections.singletonList(new TaskResponseDto(
        taskId, "Test", "Test", "PENDING", "HIGH", authorId,
        executorId, "Test", taskDateTime));

    when(taskService.getAllTasks(page, limit)).thenReturn(allResponses);

    var response = taskController.getAllTasksPageable(page, limit);

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertEquals(allResponses, response.getBody());
    verify(taskService, times(1)).getAllTasks(page, limit);
  }

  @Test
  @WithMockUser(roles = {"ADMIN"})
  void testDeleteTask() {
    UUID taskId = UUID.fromString("014366cb-c3d7-42bb-af0d-d5f9d6937bf7");
    DeleteTaskResponseDto responseDto = new DeleteTaskResponseDto(taskId, "Test");

    when(taskService.deleteTask(taskId)).thenReturn(responseDto);

    var response = taskController.deleteTask(taskId);

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertEquals(responseDto, response.getBody());
    verify(taskService, times(1)).deleteTask(taskId);
  }

}

