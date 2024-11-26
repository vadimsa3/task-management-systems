package com.example.taskmanagementsystems.impl.exception;


import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {

  String uri;

  String type;

  String message;

  @JsonFormat(pattern = "yyyy-MM-dd hh:mm:SSS")
  LocalDateTime timestamp;

}
