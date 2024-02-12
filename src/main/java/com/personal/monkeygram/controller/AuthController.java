package com.personal.monkeyGram.controller;

import com.personal.monkeyGram.model.User;
import com.personal.monkeyGram.security.auth.JwtRequest;
import com.personal.monkeyGram.security.auth.JwtResponse;
import com.personal.monkeyGram.service.AuthService;
import com.personal.monkeyGram.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Auth Controller", description = "Auth API")
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/login")
    public JwtResponse login(@RequestBody JwtRequest jwtRequest) {
        log.info("Login request");
        return authService.login(jwtRequest);
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        log.info("Registration request");
        return userService.addUser(user);
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(@RequestBody String refreshToken) {
        log.info("Refresh token request");
        return authService.refresh(refreshToken);
    }
}
