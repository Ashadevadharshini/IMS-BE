package com.example.interview_scheduler.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String candidateId;

    private String email;

    private Integer level;

    private String meetingLink;

    private String panelName;

    private LocalDate interviewDate;

    private LocalTime interviewTime;

    @Enumerated(EnumType.STRING)
    private Status result;
}