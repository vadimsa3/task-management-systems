package com.example.taskmanagementsystems.api.annotation.auth_contr;

import com.example.taskmanagementsystems.api.dto.response.CheckRegistrationResponseDto;
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
            schema = @Schema(implementation = CheckRegistrationResponseDto.class),
            examples = @ExampleObject(
                value = "{\"isRegistered\": \"true\"}"))),

    @ApiResponse(responseCode = "401",
        description = "Unauthorized (попытка запроса от неавторизованного пользователя "
            + "- без передачи / некорректного значения JWT token в Header",
        content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class),
            examples = @ExampleObject(
                value = "{\"path\": \"/api/user-service/user/check\","
                    + "\"error\": \"Unauthorized\","
                    + "\"message\": \"Full authentication is required to access this resource\","
                    + "\"status\": \"401\"}")))
})
public @interface CheckRegistrationOperation {

}
