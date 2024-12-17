package com.example.taskmanagementsystems.impl.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.net.ConnectException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler
  public ResponseEntity<ErrorMessage> handleCustomException(AppException appException,
      HttpServletRequest httpServletRequest) {
    log.error("Error occurred. Exception: {}. Message: {}",
        appException.getEnumInterface().getException(),
        appException.getErrorMessage());
    return ResponseEntity
        .status(appException.getHttpStatus())
        .body(ErrorMessage
            .builder()
            .uri(httpServletRequest.getRequestURI())
            .message(appException.getErrorMessage())
            .type(appException.getEnumInterface().getException())
            .timestamp(LocalDateTime.now())
            .build());
  }

  @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class,
      HttpMessageNotReadableException.class, MissingRequestHeaderException.class})
  public ResponseEntity<ErrorMessage> handleBadRequestException(RuntimeException exception,
      HttpServletRequest httpServletRequest) {
    log.error(exception.getMessage());
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(ErrorMessage.builder()
            .uri(httpServletRequest.getRequestURI())
            .type(HttpStatus.BAD_REQUEST.toString())
            .message(exception.getMessage())
            .timestamp(LocalDateTime.now())
            .build());
  }

  @ExceptionHandler(NoSuchElementException.class)
  public ResponseEntity<ErrorMessage> handleNotFoundException(RuntimeException exception,
      HttpServletRequest httpServletRequest) {
    log.error(exception.getMessage());
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(ErrorMessage.builder()
            .uri(httpServletRequest.getRequestURI())
            .type(HttpStatus.NOT_FOUND.toString())
            .message(exception.getMessage())
            .timestamp(LocalDateTime.now())
            .build());
  }

  @ExceptionHandler(ConnectException.class)
  public ResponseEntity<ErrorMessage> handleInternalServerError(ConnectException connectException,
      HttpServletRequest httpServletRequest) {
    log.error(connectException.getMessage());
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(ErrorMessage.builder()
            .uri(httpServletRequest.getRequestURI())
            .type(HttpStatus.INTERNAL_SERVER_ERROR.toString())
            .message(connectException.getMessage())
            .timestamp(LocalDateTime.now())
            .build());
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ErrorListMessage> onConstraintValidationException(
      ConstraintViolationException exception,
      HttpServletRequest httpServletRequest
  ) {
    List<String> messages = getAllMessages(exception);
    log.error("ConstraintViolationException: {}", String.join(", ", messages));
    return buildErrorListResponse(messages, HttpStatus.BAD_REQUEST, httpServletRequest);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorListMessage> validException(
      MethodArgumentNotValidException exception,
      HttpServletRequest httpServletRequest
  ) {
    List<String> messages = getAllMessages(exception);
    log.error("MethodArgumentNotValidException: {}", String.join(", ", messages));
    return buildErrorListResponse(messages, HttpStatus.BAD_REQUEST, httpServletRequest);
  }

  private List<String> getAllMessages(MethodArgumentNotValidException exception) {
    return exception.getBindingResult().getFieldErrors().stream()
        .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
        .collect(Collectors.toList());
  }

  private List<String> getAllMessages(ConstraintViolationException exception) {
    return exception.getConstraintViolations().stream()
        .map(ConstraintViolation::getMessage)
        .collect(Collectors.toList());
  }

  private ResponseEntity<ErrorListMessage> buildErrorListResponse(
      List<String> messages,
      HttpStatus status,
      HttpServletRequest httpServletRequest
  ) {
    return ResponseEntity.status(status)
        .body(ErrorListMessage.builder()
            .uri(httpServletRequest.getRequestURI())
            .type(status.toString())
            .messages(messages)
            .timestamp(LocalDateTime.now())
            .build());
  }

}
