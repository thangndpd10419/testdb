package com.example.foodbe.repositories;

import com.example.foodbe.dto.user.UserResponseDTO;
import com.example.foodbe.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser,Long> {
    Optional<AppUser> findByEmail(String email);
    boolean existsByEmail(String email);

}
