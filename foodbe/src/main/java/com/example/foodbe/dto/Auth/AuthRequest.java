package com.example.foodbe.dto.Auth;


public class AuthRequest {
    // DTO nhận dữ liệu login

        private String email;
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
