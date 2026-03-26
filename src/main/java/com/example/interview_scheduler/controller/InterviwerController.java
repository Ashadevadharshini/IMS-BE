//package com.example.interview_scheduler.controller;
//
//import com.example.interview_scheduler.entity.InterviewerEntity;
//import com.example.interview_scheduler.service.InterviwerService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/interviewer")
//public class InterviwerController {
//
//    private final InterviwerService interviwerService;
//    @Autowired
//    public InterviwerController(InterviwerService interviwerService) {
//        this.interviwerService = interviwerService;
//    }
//
//    @PostMapping
//    public InterviewerEntity addinterviewer(@RequestBody InterviewerEntity interviewerEntity) {
//        return interviwerService.addInterviewer(interviewerEntity);
//    }
//}