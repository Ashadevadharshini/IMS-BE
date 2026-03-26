package com.example.interview_scheduler.controller;

import com.example.interview_scheduler.dto.Userdto;
import com.example.interview_scheduler.entity.User;
import com.example.interview_scheduler.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user){
        System.out.println(user);  // 👈 add this
        return ResponseEntity.status(201).body(userService.registerUser(user));
    }

}