package com.personal.monkeyGram.controller;

import com.personal.monkeyGram.model.Post;
import com.personal.monkeyGram.service.CommentService;
import com.personal.monkeyGram.service.LikeService;
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
    private final LikeService likeService;

    @Operation(summary = "Add new post")
    @PostMapping
    public ResponseEntity<?> addPost(@RequestBody Post post) {
        return ResponseEntity.ok(postService.addPost(post));
    }

    @Operation(summary = "Delete post by id")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable String postId) {
        return ResponseEntity.ok(postService.deletePost(postId));
    }

    @Operation(summary = "Get Posts by userId")
    @GetMapping("/{userId}")
    public ResponseEntity<?> getByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(postService.findPostsByUserId(userId));
    }

    @Operation(summary = "Add new comment")
    @PostMapping("/{postId}")
    public ResponseEntity<?> addComment(@RequestBody String body, @PathVariable String postId) {
        return ResponseEntity.ok(commentService.addComment(body, postId));
    }
    @Operation(summary = "Delete comment")
    @DeleteMapping("/{postId}/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable String postId, @PathVariable String commentId) {
        return ResponseEntity.ok(commentService.deleteComment(postId, commentId));
    }
    @Operation(summary = "Get all comments")
    @GetMapping("/{postId}/comments")
    public ResponseEntity<?> getComments(@PathVariable String postId) {
        return ResponseEntity.ok(commentService.findAllByPostId(postId));
    }

    @Operation(summary = "Add like")
    @PostMapping("/{postId}/likes")
    public ResponseEntity<?> addLike(@PathVariable String postId) {
        return ResponseEntity.ok(likeService.addLike(postId));
    }

    @Operation(summary = "Remove like")
    @DeleteMapping("/{postId}/likes")
    public ResponseEntity<?> removeLike(@PathVariable String postId) {
        return ResponseEntity.ok(likeService.removeLike(postId));
    }

}
