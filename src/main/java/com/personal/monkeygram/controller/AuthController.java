package com.personal.monkeyGram.controller;

import com.personal.monkeyGram.model.User;
import com.personal.monkeyGram.security.auth.JwtRequest;
import com.personal.monkeyGram.security.auth.JwtResponse;
import com.personal.monkeyGram.service.AuthService;
import com.personal.monkeyGram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/login")
    public JwtResponse login(@RequestBody JwtRequest jwtRequest){
       return authService.login(jwtRequest);
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(@RequestBody String refreshToken) {
        return authService.refresh(refreshToken);
    }
}
