package com.example.foodbe.request.user;

import com.example.foodbe.annotation.FormatWhitespace;
import com.example.foodbe.annotation.Trim;
import com.example.foodbe.models.Role;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserCreateDTO {

    @FormatWhitespace
    @NotBlank(message = "name not empty")
    private String name;

    @NotNull(message = "age not empty")
    private int age;

    @Trim
    @NotBlank(message = "email not empty")
    @Email
    private String email;

    @Trim
    @NotBlank(message = "pass not empty")
    private String password;

    private String address;
    private String phone;
    @NotNull(message = "role not empty")
    private Role role;
}
