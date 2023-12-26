package com.personal.monkeyGram.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Getter
@Setter
@Document(collection = "Comments")
public class Comment {
    @Id
    @Schema(hidden = true)
    private String id;
    @NotBlank
    private String body;
    private String userId;
    private String postId;
    @Schema(hidden = true)
    private LocalDateTime date;

}
