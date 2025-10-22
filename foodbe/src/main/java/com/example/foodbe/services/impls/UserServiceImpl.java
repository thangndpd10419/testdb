package com.example.foodbe.services.impls;

import com.example.foodbe.dto.user.UserCreateDTO;
import com.example.foodbe.dto.user.UserResponseDTO;
import com.example.foodbe.mapper.UserMapper;
import com.example.foodbe.models.AppUser;
import com.example.foodbe.repositories.UserRepository;
import com.example.foodbe.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder bCryptPasswordEncoder;


    @Override
    public void deleteById(Long id) {
        AppUser appUser = userRepository.findById(id)
                .orElseThrow(()->new RuntimeException("not found user by id: "+id));
        userRepository.deleteById(id);
    }

    @Override
    public List<UserResponseDTO> findAll() {
        List<AppUser> users= userRepository.findAll();

        return users.stream().map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO findById(Long id) {
        AppUser user= userRepository.findById(id)
                .orElseThrow(()->new RuntimeException("not found by id: "+id));
        return userMapper.toDto(user);
    }

    @Override
    public UserResponseDTO create(UserCreateDTO userCreateDTO) {
        if (userRepository.existsByEmail(userCreateDTO.getEmail())) {
            throw new RuntimeException("Username already exists");
        }
        String newPass=  bCryptPasswordEncoder.encode(userCreateDTO.getPassword());
        userCreateDTO.setPassword(newPass);
        AppUser user= userMapper.toEntity(userCreateDTO);

        return userMapper.toDto(userRepository.save(user));
    }
}
