package com.personal.monkeyGram.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collection = "Users")
public class User {
    @Id
    private String id;
    @NotNull(message = "Username must be not null")
    @NotBlank
    private String username;
    private String nickname;
    private String password;
    private List<Role> roles;

    public User(String username, String nickname, String password, List<Role> roles) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.roles = roles;
    }
}
