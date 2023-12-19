package com.personal.monkeyGram.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "Posts")
public class Post {
    @Id
    private String id;
    @DBRef
    private User user;
    private String description;
    private Long likes;
    private String url;
    private LocalDateTime date;

    public Post(User user, String description, String url) {
        this.user = user;
        this.description = description;
        this.likes = 0L;
        this.url = url;
        this.date = LocalDateTime.now();
    }
}
