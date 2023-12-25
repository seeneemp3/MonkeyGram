package com.personal.monkeyGram.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Set;

@Setter
@Getter
public class Follow {
    @Id
    private String id;
    private String userId;
    private Set<String> followers;
    private Set<String> followed;

    public Follow(String userId, Set<String> followers, Set<String> followed) {
        this.userId = userId;
        this.followers = followers;
        this.followed = followed;
    }
}
