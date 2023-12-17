package com.personal.monkeyGram.service;

import com.personal.monkeyGram.security.auth.JwtRequest;
import com.personal.monkeyGram.security.auth.JwtResponse;

public interface AuthService {

    JwtResponse login(JwtRequest loginRequest);

    JwtResponse refresh(String refreshToken);

}