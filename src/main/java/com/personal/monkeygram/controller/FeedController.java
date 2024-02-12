package com.personal.monkeyGram.controller;

import com.personal.monkeyGram.model.Post;
import com.personal.monkeyGram.service.FeedService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Feed Controller", description = "Feed API")
@RequestMapping("/api/v1/feed")
@RequiredArgsConstructor
public class FeedController {
    private final FeedService feedService;

    @GetMapping
    public List<Post> top10Liked() {
        return feedService.getTop10Rated();
    }
}
