package com.example.interview_scheduler.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Data
public class Userdto {
    private String name;
    private String email;
    private Long employeeId;
    private Set<String> roles;
    private Long phoneNumber;
}