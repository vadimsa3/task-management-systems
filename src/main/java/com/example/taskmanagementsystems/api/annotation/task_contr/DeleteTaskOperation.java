package com.example.taskmanagementsystems.api.annotation.task_contr;

import com.example.taskmanagementsystems.api.dto.response.DeleteTaskResponseDto;
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
            schema = @Schema(implementation = DeleteTaskResponseDto.class),
            examples = @ExampleObject(
                value = "{\"task_id\": \"b044d364-4ee2-4d2b-aae4-baca1ec7ded8\","
                    + " \"title\": \"Разработка системы управления задачами\"}")
        )),

    @ApiResponse(responseCode = "400",
        description = "BadRequestException",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class),
            examples = @ExampleObject(
                value =
                    "{\"uri\": \"/api/task-service/admin/b044d364-4ee2-4d2b-aae4-baca1ec7ded8\","
                        + "\"type\": \"NotFoundException\","
                        + "\"message\": \"Task with ID b044d364-4ee2-4d2b-aae4-baca1ec7ded8 not found\","
                        + "\"timestam\": \"2024-11-26 11:15:453\"}")
        )),

    @ApiResponse(responseCode = "401",
        description = "Unauthorized",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class),
            examples = @ExampleObject(
                value =
                    "{\"path\": \"/api/task-service/admin/b044d364-4ee2-4d2b-aae4-baca1ec7ded8\","
                        + "\"error\": \"Unauthorized\","
                        + "\"message\": \"Full authentication is required to access this resource\","
                        + "\"status\": \"401\"}")))

})

public @interface DeleteTaskOperation {

}
