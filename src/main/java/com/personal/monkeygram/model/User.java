package com.personal.monkeyGram.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Getter
@Setter
@Builder
@Document(collection = "Users")
public class User {
    @Id
    private String id;
    private String username;
    private String nickname;
}
