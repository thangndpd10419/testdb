package com.example.foodbe.services;

import com.example.foodbe.dto.auth.AuthRequest;
import com.example.foodbe.dto.auth.AuthResponse;

public interface AuthService {
    AuthResponse authenticate(AuthRequest authRequest);
}
