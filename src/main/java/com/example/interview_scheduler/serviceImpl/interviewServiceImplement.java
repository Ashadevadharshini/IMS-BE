package com.example.interview_scheduler.serviceImpl;

import com.example.interview_scheduler.entity.Candidatelist;
import com.example.interview_scheduler.entity.InterviewEntity;
import com.example.interview_scheduler.entity.Status;
import com.example.interview_scheduler.repository.CandidatelistRepo;
import com.example.interview_scheduler.repository.InterviewRepo;
import com.example.interview_scheduler.service.EmailService;
import com.example.interview_scheduler.service.InterviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class interviewServiceImplement implements InterviewService {
    private  final CandidatelistRepo candidatelistRepo;
    private  final InterviewRepo interviewRepo;
    private final EmailService emailService;
    @Override
    public void allocation() {

    }
    @Override
    public InterviewEntity updateInterviewResult(Long id, Status result) {

        InterviewEntity interview = interviewRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Interview Not Found"));

        Candidatelist candidate = candidatelistRepo
                .findByCandidateId(interview.getCandidateId())
                .orElseThrow(() -> new RuntimeException("Candidate Not Found"));

        interview.setResult(result);
        interviewRepo.save(interview);

        if (result == Status.SELECTED) {

            if (candidate.getLevel() == null) {
                candidate.setLevel(1);
            } else {
                candidate.setLevel(candidate.getLevel() + 1);
            }

            candidate.setStatus(Status.PENDING);

        } else {
            candidate.setStatus(Status.REJECTED);
        }

        candidatelistRepo.save(candidate);

        return interview;
    }
    @Override
    public InterviewEntity scheduleInterview(InterviewEntity interview) {


        interview.setResult(Status.PENDING);


        InterviewEntity saved = interviewRepo.save(interview);

        Candidatelist candidate = candidatelistRepo
                .findByCandidateId(interview.getCandidateId())
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        emailService.sendInterviewMail(
                candidate.getEmail(),
                candidate.getName(),
                interview.getInterviewDate().toString(),
                interview.getInterviewTime().toString(),
                interview.getMeetingLink()
        );

        return saved;
    }
}
