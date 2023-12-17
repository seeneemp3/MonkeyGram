package com.personal.monkeyGram.security.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Request for login")
public class JwtRequest {

    @Schema(description = "username", example = "user1")
    @NotNull(message = "Username must be not null.")
    private String username;

    @Schema(description = "password", example = "123")
    @NotNull(message = "Password must be not null.")
    private String password;

}
