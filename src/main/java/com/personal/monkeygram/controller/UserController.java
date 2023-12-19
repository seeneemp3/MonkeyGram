package com.personal.monkeyGram.controller;

import com.personal.monkeyGram.model.User;
import com.personal.monkeyGram.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "User Controller", description = "User API")
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "Add new user")
    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.addUser(user));
    }

    @Operation(summary = "Delete user by id")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@RequestParam String userId) {
        return ResponseEntity.ok(userService.deleteUser(userId));
    }

    @Operation(summary = "Update user")
    @PatchMapping()
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(user));
    }

    @Operation(summary = "Get all users")
    @GetMapping()
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUserByUsername(@RequestParam String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @Operation(summary = "Get User by id")
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@RequestParam String userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }
}
