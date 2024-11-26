package com.example.taskmanagementsystems.api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;

@Builder
public record UserResponseDto(
    UUID userId,
    String firstName,
    String lastName,
    String email,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    LocalDateTime userRegistrationDate
) {

}
