package com.example.taskmanagementsystems.api.annotation.user_contr;

import com.example.taskmanagementsystems.api.dto.response.MessageResponseDto;
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
                value = "{\"message\": \"Congratulations! You an ADMIN now.\"}")
        )),

    @ApiResponse(responseCode = "404",
        description = "Not Found",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class),
            examples = @ExampleObject(
                value = "{\"uri\": \"/api/user-service/auth/login\","
                    + "\"type\": \"NotFoundException\","
                    + "\"message\": \"User with Id f05cdd27-d303-49f6-9bbc-301a0428c738 not found\","
                    + "\"timestam\": \"2024-11-25 05:22:130\"}")
        )),

    @ApiResponse(responseCode = "401",
        description = "Unauthorized",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class),
            examples = @ExampleObject(
                value =
                    "{\"path\": \"/api/user-service/user/update/17bc0f0a-2a2e-4362-8ebc-8eddd65095d1\","
                        + "\"error\": \"Unauthorized\","
                        + "\"message\": \"Full authentication is required to access this resource\","
                        + "\"status\": \"401\"}")))

})

public @interface ChangeUserRoleOperation {

}
