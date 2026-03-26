package com.example.interview_scheduler.controller;

import com.example.interview_scheduler.entity.InterviewEntity;
import com.example.interview_scheduler.entity.Status;
import com.example.interview_scheduler.service.InterviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/interview")
@RequiredArgsConstructor
@CrossOrigin
public class InterviewController {

    private final InterviewService interviewService;

    @PostMapping("/allocation")
    public String allocation() {
        interviewService.allocation();
        return "Interview Allocated Successfully";
    }

    @PostMapping("/schedule")
    public InterviewEntity scheduleInterview(@RequestBody InterviewEntity interview) {
        return interviewService.scheduleInterview(interview);
    }

    @PutMapping("/result/{id}")
    public InterviewEntity updateResult(
            @PathVariable Long id,
            @RequestParam Status result) {

        return interviewService.updateInterviewResult(id, result);
    }
}