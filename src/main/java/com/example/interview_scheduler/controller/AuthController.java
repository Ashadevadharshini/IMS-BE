package com.example.interview_scheduler.controller;

import com.example.interview_scheduler.config.JwtUtil;
import com.example.interview_scheduler.dto.Userdto;
import com.example.interview_scheduler.entity.User;
import com.example.interview_scheduler.repository.UserRepo;
import com.example.interview_scheduler.request.LoginRequest;
import com.example.interview_scheduler.response.AuthResponse;
import com.example.interview_scheduler.serviceImpl.CustomUserDetailsService;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;

    public AuthController(JwtUtil jwtUtil,
                          CustomUserDetailsService customUserDetailsService,
                          PasswordEncoder passwordEncoder,
                          UserRepo userRepo) {
        this.jwtUtil = jwtUtil;
        this.customUserDetailsService = customUserDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
    }

    // 🔐 LOGIN API
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

    @GetMapping("/me")
    public Userdto getCurrentUser(Authentication authentication) {

        if (authentication == null) {
            throw new RuntimeException("Unauthorized - Token missing");
        }

        String email = authentication.getName();

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Userdto dto = new Userdto();
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setEmployeeId(user.getEmployeeId());
        dto.setRoles(user.getRoles());
        dto.setPhoneNumber(user.getPhoneNumber());

        return dto;
    }
}