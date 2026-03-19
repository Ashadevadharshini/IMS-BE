package com.example.interview_scheduler.controller;

import com.example.interview_scheduler.config.JwtUtil;
import com.example.interview_scheduler.request.LoginRequest;
import com.example.interview_scheduler.response.AuthResponse;
import com.example.interview_scheduler.serviceImpl.CustomUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(JwtUtil jwtUtil,
                          CustomUserDetailsService customUserDetailsService,
                          PasswordEncoder passwordEncoder) {
        this.jwtUtil = jwtUtil;
        this.customUserDetailsService = customUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {

        UserDetails userDetails = customUserDetailsService
                .loadUserByUsername(request.getEmail());

        if (passwordEncoder.matches(request.getPassword(), userDetails.getPassword())) {

            String role = userDetails.getAuthorities()
                    .iterator().next()
                    .getAuthority()
                    .replace("ROLE_", "");

            String token = jwtUtil.generateToken(request.getEmail(), role);

            return new AuthResponse(token, role);

        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }
}