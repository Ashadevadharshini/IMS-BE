package com.example.interview_scheduler.serviceImpl;

import com.example.interview_scheduler.entity.Candidatelist;
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
                    !getCellValue(header.getCell(1)).equalsIgnoreCase("name") ||
                    !getCellValue(header.getCell(2)).equalsIgnoreCase("email")) {

                throw new RuntimeException("Invalid Excel format. Expected columns: Name, Email, Phone, Skill, Degree, Experience, Status");
            }

            List<String> existingEmails = repo.findAllEmails();
            Set<String> emailSet = new HashSet<>(existingEmails);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);
                if (row == null) continue;

                String name = getCellValue(row.getCell(1));
                String email = getCellValue(row.getCell(2));
                String phone = getCellValue(row.getCell(3));


                if (name.isEmpty() || email.isEmpty()) continue;

                if (emailSet.contains(email)) continue;

                Candidatelist c = new Candidatelist();

                c.setName(name);
                c.setEmail(email);
                c.setPhone(phone);
                c.setSkill(getCellValue(row.getCell(4)));
                c.setDegree(getCellValue(row.getCell(5)));
                c.setExperience(getCellValue(row.getCell(6)));
                c.setStatus(getCellValue(row.getCell(7)));

                candidates.add(c);


                emailSet.add(email);
            }
            repo.saveAll(candidates);

        } catch (Exception e) {
            throw new RuntimeException("Invalid or corrupted Excel file. Please upload correct format.", e);
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
}