package com.techacademy.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.techacademy.entity.Report;
import com.techacademy.repository.ReportRepository;
import com.techacademy.service.ReportService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ReportController {

    private final ReportService reportService;
    private final ReportRepository reportRepository;

    @Autowired
    public ReportController(
            ReportService reportService,
            ReportRepository reportRepository ) {
        this.reportService = reportService;
        this.reportRepository = reportRepository;
    }

    // index
    @GetMapping(value = "/")
    public String getListPage(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false) String order,
            Model model) throws Exception {

        // 全データ 取得
        List<Report> reportList = reportService.findAll();

        // 全データ 数える
        int allDataCount = reportList.size();
        model.addAttribute("listSize", allDataCount);

        // 現在のページ ＝ 遷移先ページ数 格納
        model.addAttribute("activePage", page);

        // page情報から start位置 と end位置 を確定
        int start = ( 5 * page ) - 5;
        int end   =   5 * page;
        if ( allDataCount <= end ) { end = allDataCount; }

        // 昇順 or 降順 or null
               if( "asc".equals(order) ) {

            Comparator<Report> asc = Comparator.comparing(Report::getEmployeeCode);
            List<Report> pagedItems = reportList.stream().sorted(asc).collect(Collectors.toList()).subList(start, end);

            model.addAttribute("reportList", pagedItems);

        } else if( "desc".equals(order) ) {

            Comparator<Report> desc = Comparator.comparing(Report::getEmployeeCode).reversed();
            List<Report> pagedItems = reportList.stream().sorted(desc).collect(Collectors.toList()).subList(start, end);

            model.addAttribute("reportList", pagedItems);

        } else {

            List<Report> pagedItems = reportList.subList(start, end);

            model.addAttribute("reportList", pagedItems);
        }

        // order情報格納
        model.addAttribute("order", order);

        // ページング情報格納
        int startPage = 1;
        int endPage   = ( allDataCount + 5 - 1) / 5;
        if ( allDataCount <= endPage ) { endPage = allDataCount; }

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "r-list";
    }

    // アカウントIDから一覧取得
    @PostMapping(value = "/search/" )
    public String getByCode(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false) String empcode,
            HttpServletRequest request, Model model) throws Exception {

        // 空なら終了
        if ( empcode.isEmpty() ) { return "redirect:/"; }

        // 表示データ取得
        List<Report> reportList = reportService.findByEmployeeCode(empcode);

        // 検索結果 データ数 数える
        int dataCount = reportList.size();

        int start = ( 5 * page ) - 5;
        int end   =   5 * page;
        if ( dataCount <= end ) { end = dataCount; }

        List<Report> pagedItems = reportList.subList(start, end);

        model.addAttribute("empcode", empcode);
        model.addAttribute("listSize", dataCount);
        model.addAttribute("reportList", pagedItems);

        // ページング情報格納
        int startPage = 1;
        int endPage   = ( dataCount + 5 - 1) / 5;
        if ( dataCount <= endPage ) { endPage = dataCount; }

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        // 遷移先ページ数取得
        model.addAttribute("acPage", page);

        return "r-list";
    }

    // アカウントID絞込時 ページング
    @GetMapping(value = "/search/" )
    public String getByCode2(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false) String empcode,
            HttpServletRequest request, Model model) throws Exception {

        return getByCode(page, empcode, request, model);
    }

    // 詳細画面
    @GetMapping(value = "/r-detail/{id}/")
    public String detail(@PathVariable String id,Model model) {

        model.addAttribute("report", reportService.findByCode(id));

        return "r-detail";
    }

    // 更新実行
    @PostMapping(value = "/r-detail/{id}/update")
    public String update(
            @PathVariable String id,Report report,Model model) {

        model.addAttribute("report", reportService.update(id, report));

        return "redirect:/";
    }

    // 削除
    @GetMapping(value = "/delete/{id}/")
    public String delete(
            @PathVariable String id,Report report,Model model) {

        model.addAttribute("report", reportService.delete(id, report));

        return "redirect:/";
    }

    // 全件取得
    @GetMapping(value = "/all/")
    public String getAllList(Model model) {

        return "redirect:/";
    }

    // CSVファイルを出力
    @GetMapping("/search/csv")
    public void exportCsv(
            @RequestParam("empcode") String empCode,
            HttpServletResponse response ) throws IOException {

        System.out.println(empCode);
        if ( empCode.isEmpty() ) {
            response.sendRedirect("/");
            return;
        }

        response.setContentType("text/csv;charset=Shift-JIS");
        response.setHeader("Content-Disposition", "attachment; filename=reports.csv");

        List<Report> reportList = reportService.findByEmployeeCode(empCode);

        try (OutputStreamWriter writer = new OutputStreamWriter(
                response.getOutputStream(), "Shift-JIS")) {

            writer.write("アカウントID,アカウント名,キーワード\n");

            for (Report report : reportList) {
                writer.write(String.format("%s,%s,%s\n",
                        report.getEmployeeCode(),
                        report.getTitle(),
                        report.getContent()));
            }
        }
    }

    // CSVファイルをインポート
    @PostMapping("/csv")
    public String importCsv(
            @RequestParam("file") MultipartFile file,
            Report report) throws IOException {

        // 格納用一時ファイル作成
        File tempFile = File.createTempFile("uploaded", null);

        // 一時ファイルにコピー
        try (OutputStream os = new FileOutputStream(tempFile)) {
            os.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 一時ファイルのフルパスを取得
        String fullPath = tempFile.getAbsolutePath();

        // pathをゲット
        Path path = Paths.get(fullPath);

        try {
            // 各行を配列に格納
            List<String> lines = Files.readAllLines(path, Charset.forName("Shift-JIS"));

            // 1行づつ取り出し
            for (int i = 1; i < lines.size(); i++) {

                String[] data = lines.get(i).split(",");

//                System.out.println("next");
//                System.out.print(data[0] + ",");
//                System.out.print(data[1] + ",");
//                System.out.print(data[2] + ",");
//                System.out.print(data[3] + ",");
//                System.out.print(data[4] + ",");
//                System.out.print(data[5] + ",");
//                System.out.print(data[6] + ",");
//                System.out.print(data[7] + ",");

                String empCode = data[4];
                report.setEmployeeCode(empCode); // empCode

                Integer ReportCounts = reportService.findAll().size() + 1;
                report.setId(ReportCounts); // id

                LocalDate RD = LocalDate.parse(data[1]);
                report.setReportDate(RD); // レポート日時
                report.setTitle(data[2]); // タイトル
                report.setContent(data[3]); // 内容

                report.setDeleteFlg(false); // deleteFlag
                LocalDateTime now = LocalDateTime.now();
                report.setCreatedAt(now); // CreatedAt
                report.setUpdatedAt(now); // UpdatedAt

                reportRepository.save(report);
            }

        } catch (IOException e) {
            return "redirect:/";
        }
        return "redirect:/";
    }
}
