package com.techacademy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.service.ReportService;

@Controller
//@RequestMapping("reports")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(
            ReportService reportService ) {
        this.reportService = reportService;
    }

//    // 日報 一覧取得
//    @GetMapping
//    public String getList() {
//        return "/r-list";
//    }

    // 日報 一覧取得
    @GetMapping()
    public String getList(Model model) {

        model.addAttribute("listSize", reportService.findAll().size());
        model.addAttribute("reportList", reportService.findAll());

        return "r-list";
    }

}
