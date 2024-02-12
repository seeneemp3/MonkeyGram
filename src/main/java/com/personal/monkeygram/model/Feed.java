package com.personal.monkeyGram.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Getter
@Setter
@Document(collection = "feed")
public class Feed {
    private String userId;
    private Set<Post> feed;

}
