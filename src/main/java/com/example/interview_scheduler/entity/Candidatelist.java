package com.example.interview_scheduler.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Candidatelist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String candidateId;

    private String name;
    private String email;
    private String phone;
    private String skill;
    private String degree;
    private String experience;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Integer level;
}