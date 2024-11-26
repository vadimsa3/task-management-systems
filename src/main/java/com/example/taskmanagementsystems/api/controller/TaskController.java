package com.example.taskmanagementsystems.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/task-service")
public interface TaskController {

  ResponseEntity<NewTaskRequestDto> addTask(Task task);


}
