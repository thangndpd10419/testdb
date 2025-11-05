package com.example.foodbe.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;   // Chuỗi bí mật trong application.properties

    @Value("${jwt.expiration}")
    private long jwtExpirationMs; // Thời gian sống token (ms)

    private SecretKey key;      // SecretKey dùng để ký token

    // -----------------------------
    // Khởi tạo SecretKey sau khi inject giá trị từ @Value
    // -----------------------------
    @PostConstruct
    public void init() {
        // HS256 yêu cầu khóa ít nhất 256 bits → convert từ chuỗi
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    // -----------------------------
    // Tạo token
    // -----------------------------
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)                      // Subject (thường là username)
                .setIssuedAt(new Date())                   // Thời gian phát hành
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs)) // Exp
                .signWith(key, SignatureAlgorithm.HS256)   // Tạo signature
                .compact(); // gộp 3 phần lại thành jwt
    }

    // -----------------------------
    // Lấy username từ token
    // -----------------------------
    public String getUsernameFromToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()        // tạo parser để giải mã(decode) token
                    .setSigningKey(key) // để xác định đúng secret tưc là đúng jwt
                    .build()
                    .parseClaimsJws(token)
                    .getBody(); // lấy payload (cliam của token) claims = payload
            return claims.getSubject(); // lấy giá trị sub trong payload là username
        } catch (JwtException e) {
            System.err.println("Error parsing token: " + e.getMessage());
            return null; // Token không hợp lệ
        }
    }

    // -----------------------------
    // Validate token
    // -----------------------------
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token); // Nếu parse ok → token hợp lệ
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            System.err.println("JWT validation error: " + e.getMessage());
        }
        return false;
    }
}
