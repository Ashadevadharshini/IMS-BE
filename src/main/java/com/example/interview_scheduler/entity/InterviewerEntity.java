package com.example.interview_scheduler.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterviewerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Interviewer_Id")
    private Integer id;

    @Column(name = "Interviewer_name")
    private String interviewerName;

    @Email
    @Column(name = "Interviewer_Email")
    private String interviewerEmail;

    @OneToMany(mappedBy = "interviewer", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<InterviewEntity> interviews;;
}