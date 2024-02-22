package com.techacademy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techacademy.entity.Report;
import com.techacademy.repository.ReportRepository;

@Service
public class ReportService {

    private final ReportRepository reportRepository;

    @Autowired
    public ReportService(
            ReportRepository reportRepository ) {
        this.reportRepository = reportRepository;
    }

    // 全件取得
    public List<Report> findAll() {
        return reportRepository.findAll();
    }

    // employeeCodeで検索表示
    public List<Report> findByEmployeeCode(String employeeCode) {
           List<Report> reportList = reportRepository.findByEmployeeCode(employeeCode);
           return reportList;
    }

    // 1件を検索
    public Report findByCode(String id) {

        Optional<Report> option = reportRepository.findById(id);

        Report report = option.orElse(null);

        return report;
    }
    
}