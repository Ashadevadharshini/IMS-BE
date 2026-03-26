package com.example.interview_scheduler.repository;

import com.example.interview_scheduler.entity.InterviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterviewRepo extends JpaRepository<InterviewEntity, Long> {
    List<InterviewEntity> findByCandidateId(String candidateId);
}