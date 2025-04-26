package com.example.online_ticketing_system.adapter.controller;


import com.example.online_ticketing_system.application.dto.user.UserCreateDTO;
import com.example.online_ticketing_system.application.dto.user.UserResponseDTO;
import com.example.online_ticketing_system.application.dto.user.UserUpdateDTO;
import com.example.online_ticketing_system.application.service.impl.UserServiceImpl;
import com.example.online_ticketing_system.domain.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController{
    //TÖVSİYƏLƏR (Professional Arxitektura üçün)
    //1. Interface-ə bağımlı ol (UserServiceImpl yox, UserService)

    private final UserService userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public UserResponseDTO createUser(@RequestBody UserCreateDTO userCreateDTO) {
        return userService.createUser(userCreateDTO);
    }

    @PutMapping("/{id}")
    public UserResponseDTO updateUser(@PathVariable Long id, @RequestBody UserUpdateDTO userUpdateDTO) {
        return userService.updateUser(id, userUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
