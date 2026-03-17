package com.example.interview_scheduler.dto;

import lombok.Data;

import java.util.Set;

@Data
public class Userdto {
    private String name;
    private String email;
    private String password;
    private Set<String> role;
}
