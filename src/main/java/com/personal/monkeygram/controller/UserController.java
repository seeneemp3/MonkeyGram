package com.personal.monkeyGram.controller;

import com.personal.monkeyGram.model.User;
import com.personal.monkeyGram.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "User Controller", description = "User API")
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @Operation(summary = "Delete user by id")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        log.info("Delete user by id request");
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @Operation(summary = "Update user")
    @PatchMapping()
    public ResponseEntity<?> updateUser(@Valid @RequestBody User user) {
        log.info("Delete user by id request");
        return ResponseEntity.ok(userService.updateUser(user));
    }

    @Operation(summary = "Get all users")
    @GetMapping()
    public ResponseEntity<?> getAllUsers() {
        log.info("Get all users");
        return ResponseEntity.ok(userService.getAll());
    }

    @Operation(summary = "Get User by username")
    @GetMapping("/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        log.info("Get user by username");
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @Operation(summary = "Get User by id")
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) {
        log.info("Get User by id");
        return ResponseEntity.ok(userService.getUserById(id));
    }

}
