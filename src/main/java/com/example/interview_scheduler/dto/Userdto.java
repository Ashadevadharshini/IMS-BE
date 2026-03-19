package com.example.interview_scheduler.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Data
public class Userdto {
    private String name;
    private String email;
    private String password;
    private Long employeeId;
    private Long phoneNumber;
    private Set<String> role;
}