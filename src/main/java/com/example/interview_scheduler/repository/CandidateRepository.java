package com.example.interview_scheduler.repository;

import com.example.interview_scheduler.entity.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<CandidateEntity,Integer> {

}