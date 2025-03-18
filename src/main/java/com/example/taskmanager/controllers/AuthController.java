package com.example.taskmanager.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskmanager.models.AuthResponse;
import com.example.taskmanager.models.LoginRequest;
import com.example.taskmanager.models.RegisterRequest;
import com.example.taskmanager.models.User;
import com.example.taskmanager.services.UserService;
import com.example.taskmanager.utils.JwtUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        User newUser = userService.registerUser(request.getUsername(),request.getPassword(),request.getRole());
        String token = jwtUtil.generateToken(newUser); // Generar token
        return ResponseEntity.ok(new AuthResponse(token)); // Devolver token
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String token = userService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(token);
    }
}
