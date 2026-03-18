package com.example.interview_scheduler.controller;

import com.example.interview_scheduler.ExcelReader.ExcelReader;
import com.example.interview_scheduler.entity.CandidateEntity;
import com.example.interview_scheduler.service.CandidateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    private final CandidateService candidateservice;

    public CandidateController(CandidateService candidateservice) {
        this.candidateservice = candidateservice;
    }

    @PostMapping("/upload")
    public String uploadCandidates(@RequestParam("file") MultipartFile file) {
        try {
            List<CandidateEntity> candidates = ExcelReader.readCandidates(file.getInputStream());
            candidateservice.saveAllCandidates(candidates);
            return "Candidates uploaded successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}