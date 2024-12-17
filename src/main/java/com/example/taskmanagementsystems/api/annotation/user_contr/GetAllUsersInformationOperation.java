package com.example.taskmanagementsystems.api.annotation.user_contr;

import com.example.taskmanagementsystems.api.dto.response.UserProfileResponseDto;
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
            schema = @Schema(implementation = UserProfileResponseDto.class),
            examples = {
                @ExampleObject(
                    value = "{\"user_id\": \"f05cdd27-d303-49f6-9bbc-301a0428c738\","
                        + "\"firstName\": \"Иван\","
                        + "\"lastName\": \"Иванов\","
                        + "\"email\": \"vanya@mail.ru\","
                        + "\"userRegistrationDate\": \"2024-11-25 06:04:51\"}"
                ),
            }
        )
    ),

    @ApiResponse(responseCode = "404",
        description = "Not Found",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class),
            examples = @ExampleObject(
                value = "{\"uri\": \"/api/user-service/auth/login\","
                    + "\"type\": \"NotFoundException\","
                    + "\"message\": \"Users not found\","
                    + "\"timestam\": \"2024-11-25 05:22:130\"}")))
})

public @interface GetAllUsersInformationOperation {

}
