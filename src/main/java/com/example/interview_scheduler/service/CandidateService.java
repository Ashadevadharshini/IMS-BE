package com.example.interview_scheduler.service;

import com.example.interview_scheduler.entity.CandidateEntity;
import java.util.List;

public interface CandidateService {
    void saveAllCandidates(List<CandidateEntity> candidates);
}