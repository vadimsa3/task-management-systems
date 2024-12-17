package com.example.taskmanagementsystems.api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;

@Builder
public record TaskResponseDto(
    UUID taskId,
    String title,
    String description,
    String status,
    String priority,
    UUID authorId,
    UUID executorId,
    String comment,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    LocalDateTime taskCreateDate
) {

}
