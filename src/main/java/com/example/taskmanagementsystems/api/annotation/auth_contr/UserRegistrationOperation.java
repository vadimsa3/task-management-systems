package com.example.taskmanagementsystems.api.annotation.auth_contr;

import com.example.taskmanagementsystems.api.dto.response.RegistrationUserResponseDto;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)

@ApiResponses(value = {

    @ApiResponse(responseCode = "201",
        description = "Created",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = RegistrationUserResponseDto.class),
            examples = @ExampleObject(
                value = "{\"user_id\": \"c906d259-e68e-4aa1-b9c9-67ca89f959dc\","
                    + " \"user_registration_date\": \"2024-11-25 04:56:32\"}"))),

})

public @interface UserRegistrationOperation {

}