package com.example.online_ticketing_system.presentation.controller.auth;


import com.example.online_ticketing_system.application.dto.auth.LoginRequest;
import com.example.online_ticketing_system.application.dto.auth.LoginResponse;
import com.example.online_ticketing_system.application.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        // Token-based logout front-end'də olur (token silinir), burda sadəcə mesaj qaytarmaq kifayətdir.
        return ResponseEntity.ok(Map.of("message", "Logged out"));
    }
}

