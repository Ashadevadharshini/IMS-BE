package com.example.interview_scheduler.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interview_id")
    private Integer interview_id;

    private LocalDate interview_date=LocalDate.now();
    private LocalTime interview_time=LocalTime.now();
    private String meetlink;

    @OneToOne
    @JoinColumn(name = "candidate_id",unique = true)
    private CandidateEntity candidate;

    //  Many Interviews → One Interviewer
    @ManyToOne
    @JoinColumn(name = "interviewer_id")
    private InterviewerEntity interviewer;
}