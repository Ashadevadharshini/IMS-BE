package com.example.interview_scheduler.ExcelReader;

import com.example.interview_scheduler.entity.CandidateEntity;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {

    public static List<CandidateEntity> readCandidates(InputStream inputStream) throws Exception {
        List<CandidateEntity> candidates = new ArrayList<>();

        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0); // first sheet

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue; // skip header

            CandidateEntity candidate = new CandidateEntity();

            candidate.setId(Integer.parseInt(getCellValueAsString(row.getCell(0)))); // S.NO
            candidate.setCandidateName(getCellValueAsString(row.getCell(1)));
            candidate.setEmail(getCellValueAsString(row.getCell(2)));
            candidate.setPhoneNumber(getCellValueAsString(row.getCell(3))); // Phone as String
            candidate.setSkills(getCellValueAsString(row.getCell(4)));
            candidate.setDegree(getCellValueAsString(row.getCell(5)));
            candidate.setExperience(getCellValueAsString(row.getCell(6)));
            candidate.setStatus(getCellValueAsString(row.getCell(7)));

            candidates.add(candidate);
        }
        workbook.close();
        return candidates;
    }

    private static String getCellValueAsString(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf((long) cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }
}