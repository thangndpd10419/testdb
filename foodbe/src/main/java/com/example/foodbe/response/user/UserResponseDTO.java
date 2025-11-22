package com.example.foodbe.dto.user;

import com.example.foodbe.models.Role;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {
    private Long id;
    private String name;
    private int age;
    private String email;
    private String password;
    private String address;
    private String phone;
    private Role role;
}
