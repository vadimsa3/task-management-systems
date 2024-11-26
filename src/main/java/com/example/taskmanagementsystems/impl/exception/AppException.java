package com.example.taskmanagementsystems.impl.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AppException extends RuntimeException {

  private EnumInterface enumInterface;

  private HttpStatus httpStatus;

  private String errorMessage;

  public AppException(EnumInterface enumInterface, String errorMessage) {
    this.enumInterface = enumInterface;
    this.httpStatus = enumInterface.getHttpStatus();
    this.errorMessage = errorMessage;
  }

  public AppException(EnumInterface enumInterface) {
    this.enumInterface = enumInterface;
    this.httpStatus = enumInterface.getHttpStatus();
    this.errorMessage = enumInterface.getErrorMessage();
  }

}
