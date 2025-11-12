package com.example.foodbe.services.impls;

import com.example.foodbe.config.JwtUtil;
import com.example.foodbe.dto.auth.AuthRequest;
import com.example.foodbe.dto.auth.AuthResponse;
import com.example.foodbe.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public AuthResponse authenticate(AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
        );

        List<String> roles = authentication.getAuthorities().stream()
                .map(a -> a.getAuthority())
                .collect(Collectors.toList());

        String token = jwtUtil.generateToken(authRequest.getEmail(), roles);
        Instant expiresAt = jwtUtil.extractExpiration(token).toInstant();

        return AuthResponse.builder()
                .token(token)
                .tokenType("Bearer")
                .roles(roles)
                .expiresAt(expiresAt)
                .build();
    }

}
