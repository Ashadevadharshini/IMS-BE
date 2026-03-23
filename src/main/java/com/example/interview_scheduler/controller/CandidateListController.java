package com.example.interview_scheduler.controller;

import com.example.interview_scheduler.entity.Candidatelist;
import com.example.interview_scheduler.service.CandidateListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
@CrossOrigin("*")
public class CandidateListController {
    @Autowired
    private CandidateListService service;

    @PostMapping("/upload")
    public List<Candidatelist> upload(@RequestParam("file") MultipartFile file) {
        return service.uploadExcel(file);
    }

    @GetMapping
    public List<Candidatelist> getAll() {
        return service.getAllCandidates();
    }
}
