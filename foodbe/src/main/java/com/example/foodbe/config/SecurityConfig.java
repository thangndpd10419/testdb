package com.example.foodbe.config;


import com.example.foodbe.services.impls.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Tắt CSRF (phù hợp khi xây dựng REST API hoặc khi sử dụng JWT)
                .csrf(csrf -> csrf.disable())

                // Cấu hình quyền truy cập cho các endpoint
                .authorizeHttpRequests(authz -> authz
                        //.requestMatchers("/public/**").permitAll()  // Các endpoint công khai
                        .requestMatchers("/api/categories/**").permitAll()
                        .requestMatchers("/api/users").permitAll()
                        .requestMatchers("/api/users/*").permitAll()
                        .requestMatchers("/api/users/**").hasAnyRole("User","Admin")
                        .anyRequest().authenticated()  // Các endpoint khác yêu cầu đăng nhập
                )

                // Cấu hình login mặc định (nếu cần thiết cho ứng dụng web)
                .formLogin(withDefaults())  // Dùng form login mặc định
                .logout(withDefaults());  // Cấu hình logout mặc định

        return http.build();  // Trả về SecurityFilterChain
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());

        return authenticationManagerBuilder.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Mã hóa mật khẩu với BCrypt
    }
}

