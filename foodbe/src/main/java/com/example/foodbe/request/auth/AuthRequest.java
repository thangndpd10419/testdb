package com.example.foodbe.request.auth;


import com.example.foodbe.annotation.Trim;

public class AuthRequest {
    // DTO nhận dữ liệu login
        @Trim
        private String email;
        @Trim
        private String password;

        // getters, setters
        public String getEmail() {
            return email;
        }
        public void setEmail(String email) {
            this.email = email;
        }
        public String getPassword() {
            return password;
        }
        public void setPassword(String password) {
            this.password = password;
        }

}
