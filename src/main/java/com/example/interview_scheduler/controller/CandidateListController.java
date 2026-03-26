package com.example.interview_scheduler.controller;

import com.example.interview_scheduler.entity.Candidatelist;
import com.example.interview_scheduler.service.CandidateListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.logging.Level;

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
    @PutMapping("/{id}")
    public Candidatelist update(@PathVariable Long id, @RequestBody Candidatelist updated){
        return service.updateCandidate(id,updated);
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        service.deleteCandidate(id);
        return "Candidate deleted successfully";
    }
    @PatchMapping("/{id}/level")
    public Candidatelist updateLevel(@PathVariable Long id, @RequestParam Integer level){
        return service.updateLevel(id, level);
    }
}
