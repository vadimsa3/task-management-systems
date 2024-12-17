package com.example.taskmanagementsystems.impl.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ErrorListMessage {

  String uri;

  String type;

  List<String> messages;

  @JsonFormat(pattern = "yyyy-MM-dd hh:mm:SSS")
  LocalDateTime timestamp;

}
