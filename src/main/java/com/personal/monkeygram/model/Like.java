package com.personal.monkeyGram.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "likes")
public class Like {
    @Id
    private String id;
    private String userId;
    private String postId;

    public Like(String userId, String postId) {
        this.userId = userId;
        this.postId = postId;
    }
}
