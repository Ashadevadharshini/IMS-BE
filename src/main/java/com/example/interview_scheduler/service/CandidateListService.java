package com.example.interview_scheduler.service;

import com.example.interview_scheduler.entity.Candidatelist;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CandidateListService {
    List<Candidatelist> uploadExcel(MultipartFile file);

    List<Candidatelist> getAllCandidates();

    void deleteCandidate(Long id);

    Candidatelist updateCandidate(Long id, Candidatelist updated);

    Candidatelist updateLevel(Long id, Integer level);
}
