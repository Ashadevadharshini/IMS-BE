package com.example.interview_scheduler.repository;

import com.example.interview_scheduler.entity.InterviewerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewerRepo extends JpaRepository<InterviewerEntity,Integer> {
}