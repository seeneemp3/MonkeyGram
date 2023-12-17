package com.personal.monkeyGram.sequrity.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Request for login")
public class JwtRequest {

    @Schema(description = "email", example = "johndoe@gmail.com")
    @NotNull(message = "Username must be not null.")
    private String username;

    @Schema(description = "password", example = "123321")
    @NotNull(message = "Password must be not null.")
    private String password;

}
