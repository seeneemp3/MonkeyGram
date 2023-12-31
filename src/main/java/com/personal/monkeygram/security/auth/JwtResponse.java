package com.personal.monkeyGram.security.auth;

import lombok.Data;

@Data
public class JwtResponse {
    private String id;
    private String username;
    private String accessToken;
    private String refreshToken;
}
