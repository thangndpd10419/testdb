package com.example.foodbe.dto.user;

import com.example.foodbe.models.Role;
import lombok.*;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreateDTO {
    @NotBlank(message = "name not empty")
    private String name;
    @NotBlank(message = "age not empty")
    private int age;
    @NotBlank(message = "email not empty")
    private String email;
    @NotBlank(message = "pass not empty")
    private String password;

    private String address;
    private String phone;
    @NotBlank(message = "role not empty")
    private Role role;
}
