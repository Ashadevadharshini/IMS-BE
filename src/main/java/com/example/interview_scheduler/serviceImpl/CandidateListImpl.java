package com.example.interview_scheduler.serviceImpl;

import com.example.interview_scheduler.entity.Candidatelist;
import com.example.interview_scheduler.entity.Status;
import com.example.interview_scheduler.repository.CandidatelistRepo;
import com.example.interview_scheduler.service.CandidateListService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class CandidateListImpl implements CandidateListService {

    @Autowired
    private CandidatelistRepo repo;

    @Override
    public List<Candidatelist> uploadExcel(MultipartFile file) {

        List<Candidatelist> candidates = new ArrayList<>();

        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {

            Sheet sheet = workbook.getSheetAt(0);

            Row header = sheet.getRow(0);
            if (header == null ||
                    !getCellValue(header.getCell(0)).equalsIgnoreCase("name") ||
                    !getCellValue(header.getCell(1)).equalsIgnoreCase("email")) {

                throw new RuntimeException("Invalid Excel format");
            }

            long baseCount = repo.count();

            List<String> existingEmails = repo.findAllEmails();
            Set<String> emailSet = new HashSet<>();

            for (String e : existingEmails) {
                emailSet.add(e.trim().toLowerCase());
            }

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);
                if (row == null || row.getPhysicalNumberOfCells() == 0) continue;

                String name = getCellValue(row.getCell(0));
                String email = getCellValue(row.getCell(1)).trim().toLowerCase();
                String phone = getCellValue(row.getCell(2));

                if (name.isEmpty() || email.isEmpty()) continue;

                if (emailSet.contains(email)) continue;

                Candidatelist c = new Candidatelist();

                String candidateId = "CAND" + String.format("%03d", baseCount + candidates.size() + 1);

                c.setCandidateId(candidateId);
                c.setName(name);
                c.setEmail(email);
                c.setPhone(phone);
                c.setSkill(getCellValue(row.getCell(3)));
                c.setDegree(getCellValue(row.getCell(4)));
                c.setExperience(getCellValue(row.getCell(5)));
                c.setStatus(Status.PENDING);
                c.setLevel(1);

                candidates.add(c);
                emailSet.add(email);
            }

            repo.saveAll(candidates);

        } catch (Exception e) {
            throw new RuntimeException("Invalid or corrupted Excel file.", e);
        }

        return candidates;
    }
    @Override
    public List<Candidatelist> getAllCandidates() {
        return repo.findAll();
    }


    private String getCellValue(Cell cell) {
        if (cell == null) return "";

        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell).trim();
    }
    @Override
    public void deleteCandidate(Long id){
        repo.deleteById(id);
    }
    @Override
    public  Candidatelist updateCandidate(Long id, Candidatelist updated){
       Candidatelist existing = repo.findById(id)
               .orElseThrow(() -> new RuntimeException("Candidate Not Found"));
       existing.setName(updated.getName());
       existing.setEmail(updated.getEmail());
       existing.setPhone(updated.getPhone());
       existing.setSkill(updated.getSkill());
       existing.setDegree(updated.getDegree());
       existing.setExperience(updated.getExperience());
       existing.setStatus(updated.getStatus());
       existing.setLevel(updated.getLevel());

       return repo.save(existing);

    }

    @Override
    public Candidatelist updateLevel(Long id, Integer level) {

        Candidatelist candidatelist = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidate Not Found"));
        candidatelist.setLevel(level);
        return repo.save(candidatelist);
    }
}