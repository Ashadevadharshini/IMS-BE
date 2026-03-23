package com.example.interview_scheduler.repository;

import com.example.interview_scheduler.entity.Candidatelist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CandidatelistRepo extends JpaRepository<Candidatelist, Long> {
    @Query("SELECT c.email FROM Candidatelist c")
    List<String> findAllEmails();
}
