package com.example.taskmanagementsystems.api.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "Модель создания новой задачи")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record RegistrationTaskRequestDto(

    @Schema(description = "Тема задачи",
        example = "Разработка системы управления задачами")
    @Size(max = 100)
    String title,

    @Schema(description = "Описание задачи",
        example = "Разработать простую систему управления задачами с использованием Java, Spring.")
    @Size(max = 300)
    String description,

    @Schema(description = "Статус задачи", example = "PENDING")
    @NotNull
    String status,

    @Schema(description = "Приоритет", example = "HIGH")
    @NotNull
    String priority,

    @Schema(description = "Автор задачи", example = "pavlovTest@mail.ru")
    @NotNull
    String authorEmail,

    @Schema(description = "Исполнитель задачи", example = "lena@gmail.com")
    @NotNull
    String executorEmail,

    @Schema(description = "Комментарий", example = "Провести декомпозицию задачи")
    @Size(max = 250)
    String comment

) {

}

