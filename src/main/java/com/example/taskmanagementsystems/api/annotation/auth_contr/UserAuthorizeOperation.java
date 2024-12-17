package com.example.taskmanagementsystems.api.annotation.auth_contr;

import com.example.taskmanagementsystems.api.dto.response.MessageResponseDto;
import io.swagger.v3.oas.annotations.headers.Header;
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
            schema = @Schema(implementation = MessageResponseDto.class),
            examples = @ExampleObject(
                value = "{\"message\": \"Success\"}")),
        headers = {
            @Header(name = "Authorization",
                description = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJsb2dpbiI6IlRlc3RAbWFpbC5ydSIsImF1dGhvcml0aWVzIjoiQURNSU4iLCJzdWIiOiI0Y2E0NmQyNy1mMGM2LTQ2NGItYmE0ZC1hODFjMzljY2EzY2MiLCJpYXQiOjE3MzI1NDQyMTUsImV4cCI6MTczMjU0NjAxNX0.gm0Zp3wSWFDOFNYYMOhI3fl5BHLp5lts6i_cIchiNTQ",
                required = true,
                schema = @Schema(type = "string", example = "Bearer your_jwt_token_here"))
        }),

    @ApiResponse(responseCode = "404",
        description = "Not Found",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class),
            examples = @ExampleObject(
                value = "{\"uri\": \"/api/user-service/auth/login\","
                    + "\"type\": \"NotFoundException\","
                    + "\"message\": \"User with email Tst@mail.ru not found\","
                    + "\"timestam\": \"2024-11-25 05:22:130\"}")))
})

public @interface UserAuthorizeOperation {

}
