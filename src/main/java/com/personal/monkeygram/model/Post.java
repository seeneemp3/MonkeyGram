package com.personal.monkeyGram.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@Document(collection = "Posts")
public class Post {
    @Id
    @Schema(hidden = true)
    private String id;
    @Schema(hidden = true)
    private String userId;
    private String description;
    @Schema(hidden = true)
    private Long likes;
    private String url;
    @Schema(hidden = true)
    private LocalDateTime date;
    @Schema(hidden = true)
    private Collection<String> commentIds;

    public Post(String description, String url) {
        this.description = description;
        this.likes = 0L;
        this.url = url;
        this.date = LocalDateTime.now();
        this.commentIds = Collections.emptySet();
    }
}
