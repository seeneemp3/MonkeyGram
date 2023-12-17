package com.personal.monkeyGram.model;

import lombok.Builder;
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
