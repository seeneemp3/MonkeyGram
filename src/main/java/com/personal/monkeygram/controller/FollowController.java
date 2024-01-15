package com.personal.monkeyGram.controller;

import com.personal.monkeyGram.service.FollowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Follow Controller", description = "Follow API")
@RequestMapping("/api/v1/follow")
@RequiredArgsConstructor
@Slf4j
public class FollowController {
    private final FollowService followService;
    @Operation(summary = "Get User followers by userId")
    @GetMapping("/{id}/followers")
    public ResponseEntity<?> getFollowersByUserId(@PathVariable String id) {
        log.info("Get User followers by userId request");
        return ResponseEntity.ok(followService.getFollowers(id));
    }
    @Operation(summary = "Get User followed by userId")
    @GetMapping("/{id}/followed")
    public ResponseEntity<?> getFollowedByUserId(@PathVariable String id) {
        log.info("Get User followed by userId request");
        return ResponseEntity.ok(followService.getFollowed(id));
    }
    @Operation(summary = "Follow user by userId")
    @PostMapping("/{id}/follow")
    public ResponseEntity<?> followByUserId(@PathVariable String id) {
        log.info("Follow user by userId request");
        return ResponseEntity.ok(followService.follow(id));
    }
    @Operation(summary = "Unfollow user by userId")
    @PostMapping("/{id}/unfollow")
    public ResponseEntity<?> unfollowByUserId(@PathVariable String id) {
        log.info("Unfollow user by userId request");
        return ResponseEntity.ok(followService.unfollow(id));
    }
}
