package com.personal.monkeygram.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.personal.monkeygram.model.Post;
import com.personal.monkeygram.service.PostService;

import java.util.Collection;

@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public Collection<Post> findAll(@RequestParam String userId) {
        return postService.findPostsByUser(userId);
    }
}
