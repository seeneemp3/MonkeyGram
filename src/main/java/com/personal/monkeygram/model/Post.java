package com.personal.monkeyGram.model;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(hidden = true)
    private String id;
    private String userId;
    private String description;
    @Schema(hidden = true)
    private Long likes;
    private String url;
    @Schema(hidden = true)
    private LocalDateTime date;

    public Post(String userId, String description, String url) {
        this.userId = userId;
        this.description = description;
        this.likes = 0L;
        this.url = url;
        this.date = LocalDateTime.now();
    }
}
