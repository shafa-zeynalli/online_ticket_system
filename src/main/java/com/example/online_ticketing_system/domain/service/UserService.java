package com.example.online_ticketing_system.domain.service;

import com.example.online_ticketing_system.application.dto.user.UserCreateDTO;
import com.example.online_ticketing_system.application.dto.user.UserResponseDTO;
import com.example.online_ticketing_system.application.dto.user.UserUpdateDTO;

import java.util.List;

public interface UserService {
    UserResponseDTO getUserById(Long id);
    UserResponseDTO createUser(UserCreateDTO userCreateDTO);
    UserResponseDTO updateUser(Long id, UserUpdateDTO userUpdateDTO);
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO findByUsername(String username);
    void deleteUser(Long id);
}
