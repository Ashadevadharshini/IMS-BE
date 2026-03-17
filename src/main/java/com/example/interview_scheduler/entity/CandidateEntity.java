package com.example.interview_scheduler.entity;

import jakarta.persistence.Column;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.stereotype.Component;

@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Table(name = "ShortlistedCandidate")
public class CandidateEntity {

    @Id
    @Column(name = "Candidate_ID")
    private Integer id;

    private String CandidateName;

    @Email
    @NotBlank
    @Column(name="Email",unique = true)
    private String email;

    @NotBlank
    @Column(name="PhoneNumber",unique = true,nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String Skills;

    @Column(nullable = false)
    private String Degree;

    @Column(nullable = false)
    private String Experience;

    private String status = "Not Allocated";
}