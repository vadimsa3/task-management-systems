package com.example.taskmanagementsystems.impl.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum EnumException implements EnumInterface {

  CONFLICT(HttpStatus.CONFLICT, "The information you entered has been re-entered.",
      "ConflictException"),

  NOT_FOUND(HttpStatus.NOT_FOUND,
      "The requested resource was not found.",
      "NotFoundException"),

  BAD_REQUEST(HttpStatus.BAD_REQUEST,
      "The request could not be understood by the server due to malformed syntax.",
      "BadRequestException"),

  FORBIDDEN(HttpStatus.FORBIDDEN,
      "You do not have permission to access the requested resource.",
      "ForbiddenException"),

  NOT_ACCEPTABLE(HttpStatus.NOT_ACCEPTABLE,
      "The request cannot be processed in the requested format.",
      "NextAttemptTimeException"),

  UNAUTHORIZED(HttpStatus.UNAUTHORIZED,
      "Authentication is required and has failed or has not yet been provided.",
      "UnauthorizedException"),

  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,
      "An unexpected condition was encountered.",
      "InternalServerErrorException");

  private final HttpStatus httpStatus;

  private final String errorMessage;

  private final String exception;

  @Override
  public String getErrorMessage() {
    return errorMessage;
  }

  @Override
  public String getException() {
    return exception;
  }

  @Override
  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

}
