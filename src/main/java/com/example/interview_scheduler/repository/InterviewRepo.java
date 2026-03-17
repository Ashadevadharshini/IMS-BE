package com.example.interview_scheduler.repository;

import com.example.interview_scheduler.entity.InterviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewRepo extends JpaRepository<InterviewEntity, Integer> {
}