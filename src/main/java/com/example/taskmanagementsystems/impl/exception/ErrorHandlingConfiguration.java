package ru.aston.exceptionhandler.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.aston.exceptionhandler.exception.AppException;
import ru.aston.exceptionhandler.exception.ErrorMessage;
import ru.aston.exceptionhandler.handler.GlobalExceptionHandler;

@Configuration
public class ErrorHandlingConfiguration {

  @Bean
  public AppException appException(){
    return new AppException();
  }

  @Bean
  public ErrorMessage errorMessage(){
    return new ErrorMessage();
  }

  @Bean
  public GlobalExceptionHandler globalExceptionHandler(){
    return new GlobalExceptionHandler();
  }
}
