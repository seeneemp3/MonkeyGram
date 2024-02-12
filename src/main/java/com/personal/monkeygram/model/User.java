package com.personal.monkeyGram.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collection = "users")
public class User {
    @Id
    @Schema(hidden = true)
    private String id;
    @NotNull(message = "Username must be not null")
    @NotBlank
    private String username;
    private String nickname;
    private String password;
    @Schema(hidden = true)
    private List<Role> roles;

    public User(String username, String nickname, String password) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
    }
}
