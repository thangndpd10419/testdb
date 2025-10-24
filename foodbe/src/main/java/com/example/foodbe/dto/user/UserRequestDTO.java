//package com.example.foodbe.dto.user;
//
//import com.example.foodbe.dto.group.CreateGroup;
//import com.example.foodbe.dto.group.UpdateGroup;
//import com.example.foodbe.models.Role;
//import lombok.*;
//
//import javax.validation.constraints.Email;
//import javax.validation.constraints.Min;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;
//
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//public class UserRequestDTO {
//
//
//
//        @NotNull(message = "Id is required for update", groups = UpdateGroup.class)
//        private Long id;  // Bắt buộc khi update, không cần khi create
//
//        @NotBlank(message = "Name must not be empty", groups = {CreateGroup.class, UpdateGroup.class})
//        private String name;
//
//        @NotNull(message = "Age must not be null", groups = {CreateGroup.class, UpdateGroup.class})
//        @Min(value = 1, message = "Age must be greater than 0", groups = {CreateGroup.class, UpdateGroup.class})
//        private Integer age;
//
//        @NotBlank(message = "Email must not be empty", groups = {CreateGroup.class, UpdateGroup.class})
//        @Email(message = "Email format invalid", groups = {CreateGroup.class, UpdateGroup.class})
//        private String email;
//
//        @NotBlank(message = "Password must not be empty", groups = CreateGroup.class) // Bắt buộc khi create, không bắt buộc khi update
//        private String password;
//
//        private String address;  // Không bắt buộc
//
//        private String phone;    // Không bắt buộc
//
//        @NotNull(message = "Role must not be null", groups = {CreateGroup.class, UpdateGroup.class})
//        private Role role;
//
//
//
//}
