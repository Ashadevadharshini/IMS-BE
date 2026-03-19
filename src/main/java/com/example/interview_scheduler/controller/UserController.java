package com.example.interview_scheduler.controller;

import com.example.interview_scheduler.dto.Userdto;
import com.example.interview_scheduler.entity.User;
import com.example.interview_scheduler.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody Userdto userDTO){
        User user = userService.registerUser(userDTO);
        return ResponseEntity.ok(user);
    }
}