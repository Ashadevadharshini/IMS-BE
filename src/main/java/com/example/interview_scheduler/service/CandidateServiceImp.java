package com.example.interview_scheduler.service;

import com.example.interview_scheduler.entity.CandidateEntity;
import com.example.interview_scheduler.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CandidateServiceImp implements com.example.interview_scheduler.service.CandidateService {

    private final CandidateRepository candidaterepository;
    private final JavaMailSender mailSender;

    @Autowired
    public CandidateServiceImp(CandidateRepository candidaterepository, JavaMailSender mailSender) {
        this.candidaterepository = candidaterepository;
        this.mailSender = mailSender;
    }

   @Override
    public void saveAllCandidates(List<CandidateEntity> candidates) {
        candidaterepository.saveAll(candidates);
    }
}