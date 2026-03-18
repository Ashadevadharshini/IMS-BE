package com.example.interview_scheduler.service;

import com.example.interview_scheduler.entity.CandidateEntity;
import com.example.interview_scheduler.entity.InterviewEntity;
import com.example.interview_scheduler.entity.InterviewerEntity;
import com.example.interview_scheduler.repository.CandidateRepository;
import com.example.interview_scheduler.repository.InterviewRepo;
import com.example.interview_scheduler.repository.InterviewerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class InterviewServiceImp implements com.example.interview_scheduler.service.InterviewService {
    private final InterviewRepo interviewRepo;
    private final CandidateEntity candidateEntity;
    private final CandidateRepository candidateRepository;
    private final InterviewerRepo interviwerRepo;
    private final JavaMailSender mailSender;


    @Autowired
    public InterviewServiceImp(InterviewRepo interviewRepo, CandidateEntity candidateEntity, CandidateRepository candidateRepository, InterviewerRepo interviwerRepo, JavaMailSender mailSender) {
        this.interviewRepo = interviewRepo;
        this.candidateEntity = candidateEntity;
        this.candidateRepository = candidateRepository;
        this.interviwerRepo = interviwerRepo;
        this.mailSender = mailSender;
    }

    public String generatepassword(String email)
    {
        String password=email.substring(0,4);
        int random=new Random().nextInt(99999);
        return password+random;
    }

    @Override
    public void allocation() {
        List<CandidateEntity> candidates = candidateRepository.findAll();
        List<InterviewerEntity> interviewers = interviwerRepo.findAll();
        if (interviewers.isEmpty()) {
            throw new RuntimeException("No Interviewers available");
        }

        int hrCount = interviewers.size();

        for (int i = 0; i < candidates.size(); i++) {
            InterviewEntity interview = new InterviewEntity();
            CandidateEntity candidate12 = new CandidateEntity();

            CandidateEntity candidate = candidates.get(i);
            InterviewerEntity hr = interviewers.get(i % hrCount);
            String meetlink="https://meet.google.com/abc-defg-hij";
            String password = generatepassword(candidate.getEmail());

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(candidate.getEmail());
            mailMessage.setSubject("Welcome to Interview");
            mailMessage.setText(
                    "Hello  Dear Candidate" + "\n\n" +
                            "Your interview details:\n" +
                            "Date: " + interview.getInterview_date() + "\n" +
                            "Time: " + interview.getInterview_time()+ "\n" +
                            "Interviewer: " + hr.getInterviewerName() + "\n" +
                            "Meet Link: " + meetlink + "\n\n" +
                            "Login Password: " + password
            );

            mailSender.send(mailMessage);
            interview.setCandidate(candidate);
            interview.setInterviewer(hr);
            interview.setMeetlink(meetlink);

            interviewRepo.save(interview);
        }
        candidateRepository.saveAll(candidates);
    }
}