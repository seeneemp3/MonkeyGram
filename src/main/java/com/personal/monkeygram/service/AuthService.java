package com.personal.monkeyGram.service;

import com.personal.monkeyGram.sequrity.auth.JwtRequest;
import com.personal.monkeyGram.sequrity.auth.JwtResponse;

public interface AuthService {

    JwtResponse login(JwtRequest loginRequest);

    JwtResponse refresh(String refreshToken);

}