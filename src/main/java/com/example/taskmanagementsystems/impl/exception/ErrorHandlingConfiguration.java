package com.example.taskmanagementsystems.impl.exception;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ErrorHandlingConfiguration {

  @Bean
  public AppException appException() {
    return new AppException();
  }

  @Bean
  public ErrorMessage errorMessage() {
    return new ErrorMessage();
  }

  @Bean
  public GlobalExceptionHandler globalExceptionHandler() {
    return new GlobalExceptionHandler();
  }
}
