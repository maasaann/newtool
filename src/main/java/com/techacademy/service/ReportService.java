package com.techacademy.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    // 更新
    @Transactional
    public Report update(String id, Report report) {
        
        // 現在情報を取得
        Report existingReport = findByCode(id);

        // キーワード上書き
        existingReport.setContent(report.getContent());

        // 現在時間セット
        LocalDateTime now = LocalDateTime.now();
        existingReport.setUpdatedAt(now);

        reportRepository.save(existingReport);

        return existingReport;
    }

    // 削除
    @Transactional
    public Report delete(String id, Report report) {
        
        // 現在情報を取得
        Report existingReport = findByCode(id);

        // 上書き
        existingReport.setDeleteFlg(true);

        // 現在時間セット
        LocalDateTime now = LocalDateTime.now();
        existingReport.setUpdatedAt(now);

        reportRepository.save(existingReport);

        return existingReport;
    }

}