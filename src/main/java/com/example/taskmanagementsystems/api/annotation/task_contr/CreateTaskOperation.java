package com.example.taskmanagementsystems.api.annotation.task_contr;

import com.example.taskmanagementsystems.api.dto.response.RegistrationTaskResponseDto;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.web.ErrorResponse;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)

@ApiResponses(value = {

    @ApiResponse(responseCode = "201",
        description = "Created",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = RegistrationTaskResponseDto.class),
            examples = @ExampleObject(
                value = "{\"task_id\": \"c906d259-e68e-4aa1-b9c9-67ca89f959dc\","
                    + " \"task_create_date\": \"2024-11-26 09:08:30\"}"))),

    @ApiResponse(responseCode = "401",
        description = "Unauthorized (попытка создания задачи неавторизованным пользователем "
            + "- без передачи / некорректного значения JWT token в Header",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class),
            examples = @ExampleObject(
                value = "{\"path\": \"/api/task-service/create\","
                    + "\"error\": \"Unauthorized\","
                    + "\"message\": \"Full authentication is required to access this resource\","
                    + "\"status\": \"401\"}"))),

    @ApiResponse(responseCode = "400",
        description = "Bad Request",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class),
            examples = @ExampleObject(
                value = "{\"uri\": \"/api/task-service/create\","
                    + "\"type\": \"BadRequestException\","
                    + "\"message\": \"Executor with email petrov@mail.ru not found\","
                    + "\"timestam\": \"2024-11-26 09:07:973\"}")))

})

public @interface CreateTaskOperation {

}
