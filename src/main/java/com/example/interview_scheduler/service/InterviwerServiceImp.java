//package com.example.interview_scheduler.service;
//
//import com.example.interview_scheduler.entity.InterviewerEntity;
//import com.example.interview_scheduler.repository.InterviewerRepo;
//import org.springframework.stereotype.Service;
//
//@Service
//public class InterviwerServiceImp implements InterviwerService {
//
//    private final InterviewerRepo interviewerRepo;
//
//    public InterviwerServiceImp(InterviewerRepo interviewerRepo) {
//        this.interviewerRepo = interviewerRepo;
//    }
//
//    @Override
//    public InterviewerEntity addInterviewer(InterviewerEntity interviewerEntity) {
//        return interviewerRepo.save(interviewerEntity);
//    }
//}