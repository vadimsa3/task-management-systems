package com.example.taskmanagementsystems.impl.exception;

import org.springframework.http.HttpStatus;

public interface EnumInterface {

  String getErrorMessage();

  String getException();

  HttpStatus getHttpStatus();

}
