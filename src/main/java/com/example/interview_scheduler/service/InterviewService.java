package com.example.interview_scheduler.service;

import com.example.interview_scheduler.entity.InterviewEntity;
import com.example.interview_scheduler.entity.Status;

import java.util.List;

public interface InterviewService {
    void allocation();
    InterviewEntity scheduleInterview(InterviewEntity interview);
    InterviewEntity updateInterviewResult(Long id, Status result);
}