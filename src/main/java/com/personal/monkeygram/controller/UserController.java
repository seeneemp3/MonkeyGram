package com.personal.monkeyGram.controller;

import com.personal.monkeyGram.model.User;
import com.personal.monkeyGram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody User user){
       return ResponseEntity.ok(userService.addUser(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@RequestParam String userId){
        return ResponseEntity.ok(userService.deleteUser(userId));
    }

    @PatchMapping()
    public ResponseEntity<?> updateUser(@RequestBody User user){
        return ResponseEntity.ok(userService.updateUser(user));
    }
    @GetMapping()
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(userService.getAll());
    }
    @GetMapping("/{username}")
    public ResponseEntity<?> getUserByUsername(@RequestParam String username){
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@RequestParam String userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }
}
