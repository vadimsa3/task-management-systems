package com.example.taskmanagementsystems.api.annotation.task_contr;

import com.example.taskmanagementsystems.api.dto.response.TaskResponseDto;
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

    @ApiResponse(responseCode = "200",
        description = "Ok",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = TaskResponseDto.class),
            examples = {
                @ExampleObject(
                    value =
                        "{\"taskId\": \"f05cdd27-d303-49f6-9bbc-301a0428c738\","
                            + " \"title\": \"Разработка системы управления задачами\","
                            + " \"description\": \"Разработать простую систему управления задачами "
                            + "с использованием Java, Spring.\","
                            + " \"status\": \"PENDING\","
                            + " \"priority\": \"HIGH\","
                            + " \"authorId\": \"1cbfce9a-fa90-4598-a601-c327d793f3e5\","
                            + " \"executorId\": \"d11d2c9e-698b-9873-ae96-8436d9ca215d\","
                            + " \"comment\": \"Провести декомпозицию задачи\","
                            + " \"taskCreateDate\": \"2024-11-26 09:08:30\"}"),
            })),

    @ApiResponse(responseCode = "400",
        description = "Bad Request",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class),
            examples = @ExampleObject(
                value = "{\"uri\": \"/api/task-service/task-by-author/6f27c94c-21a7-4ca5-b333-47ed5b03f07\","
                    + "\"type\": \"BadRequestException\","
                    + "\"message\": \"Tasks with ExecutorID 6f27c94c-21a7-4ca5-b333-047ed5b03f07 not found\","
                    + "\"timestam\": \"2024-11-26 08:11:776\"}"))),

    @ApiResponse(responseCode = "401",
        description = "Unauthorized",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class),
            examples = @ExampleObject(
                value =
                    "{\"path\": \"/api/task-service/task-by-author/6f27c94c-21a7-4ca5-b333-47ed5b03f07\","
                        + "\"error\": \"Unauthorized\","
                        + "\"message\": \"Full authentication is required to access this resource\","
                        + "\"status\": \"401\"}")))

})

public @interface GetTasksByExecutorIdOperation {

}
