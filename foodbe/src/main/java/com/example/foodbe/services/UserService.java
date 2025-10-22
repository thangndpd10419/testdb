package com.example.foodbe.services;

import com.example.foodbe.dto.user.UserCreateDTO;
import com.example.foodbe.dto.user.UserResponseDTO;

import java.util.List;

public interface UserService {
    UserResponseDTO create(UserCreateDTO userCreateDTO);
    void deleteById(Long id);
    UserResponseDTO findById(Long id);
    List<UserResponseDTO> findAll();

}