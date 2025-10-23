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
    private String secretKey;

    @Value("${jwt.expiration}")
    private long jwtExpirationMs;

    private SecretKey key;

    // Khởi tạo SecretKey từ secretKey (chuỗi) sau khi các giá trị đã được inject
    @PostConstruct
    public void init() {
        // Chuyển đổi chuỗi secretKey thành SecretKey (HS256 yêu cầu khóa dài ít nhất 256 bits)
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    // Tạo token từ username
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)                            // Username làm subject
                .setIssuedAt(new Date())                         // Thời gian phát hành token
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))  // Hết hạn
                .signWith(key, SignatureAlgorithm.HS256)         // Ký token với SecretKey
                .compact();
    }

    // Lấy username từ token
    public String getUsernameFromToken(String token) {
        try {
            return Jwts.parser()                             // Dùng parser cũ (phù hợp với jjwt 0.11.x)
                    .setSigningKey(key)                      // Sử dụng SecretKey
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (JwtException e) {
            System.err.println("Error while parsing token: " + e.getMessage());
            return null;  // Token không hợp lệ
        }
    }

    // Kiểm tra token hợp lệ chưa
    public boolean validateToken(String token) {
        try {
            Jwts.parser()                                   // Dùng parser cũ (phù hợp với jjwt 0.11.x)
                    .setSigningKey(key)                       // Sử dụng SecretKey
                    .parseClaimsJws(token);                   // Kiểm tra tính hợp lệ của token
            return true;  // Token hợp lệ
        } catch (SignatureException e) {
            System.err.println("Invalid JWT signature: " + e.getMessage());
        } catch (MalformedJwtException e) {
            System.err.println("Invalid JWT token: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.err.println("JWT token expired: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.err.println("JWT token unsupported: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("JWT claims string empty: " + e.getMessage());
        }
        return false;  // Token không hợp lệ
    }
}
