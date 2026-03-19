package com.example.interview_scheduler.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}