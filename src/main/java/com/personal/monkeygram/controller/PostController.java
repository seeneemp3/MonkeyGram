package com.personal.monkeyGram.controller;

import com.personal.monkeyGram.model.Comment;
import com.personal.monkeyGram.model.Post;
import com.personal.monkeyGram.service.CommentService;
import com.personal.monkeyGram.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Post Controller", description = "Post API")
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final CommentService commentService;

    @Operation(summary = "Add new post")
    @PostMapping
    public ResponseEntity<?> addPost(@RequestBody Post post) {
        return ResponseEntity.ok(postService.addPost(post));
    }

    @Operation(summary = "Delete post by id")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@RequestParam String postId) {
        return ResponseEntity.ok(postService.deletePost(postId));
    }

    @Operation(summary = "Get Posts by userId")
    @GetMapping("/{id}")
    public ResponseEntity<?> getByUserId(@RequestParam String userId) {
        return ResponseEntity.ok(postService.findPostsByUserId(userId));
    }

    @Operation(summary = "Add new comment")
    @PostMapping("/{postId}")
    public ResponseEntity<?> addComment(@RequestBody Comment comment, @PathVariable String postId) {
        return ResponseEntity.ok(commentService.addComment(comment));
    }
    @Operation(summary = "Delete comment")
    @PostMapping("/{postId}/{commentId}")
    public ResponseEntity<?> addComment(@PathVariable String postId, @PathVariable String commentId) {
        return ResponseEntity.ok(commentService.deleteComment(commentId));
    }

}
