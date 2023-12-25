package com.personal.monkeyGram.controller;

import com.personal.monkeyGram.service.FollowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Follow Controller", description = "Follow API")
@RequestMapping("/api/v1/follow")
@RequiredArgsConstructor
public class FollowController {
    private final FollowService followService;
    @Operation(summary = "Get User followers by userId")
    @GetMapping("/{id}/followers")
    public ResponseEntity<?> getFollowersByUserId(@RequestParam String userId) {
        return ResponseEntity.ok(followService.getFollowers(userId));
    }
    @Operation(summary = "Get User followed by userId")
    @GetMapping("/{id}/followed")
    public ResponseEntity<?> getFollowedByUserId(@RequestParam String userId) {
        return ResponseEntity.ok(followService.getFollowed(userId));
    }
    @Operation(summary = "Follow user by userId")
    @PostMapping("/{id}/follow")
    public ResponseEntity<?> followByUserId(@RequestParam String userId) {
        return ResponseEntity.ok(followService.follow(userId));
    }
    @Operation(summary = "Unfollow user by userId")
    @PostMapping("/{id}/unfollow")
    public ResponseEntity<?> unfollowByUserId(@RequestParam String userId) {
        return ResponseEntity.ok(followService.unfollow(userId));
    }
}
