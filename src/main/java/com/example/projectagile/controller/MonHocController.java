package com.example.projectagile.controller;

import com.example.projectagile.model.GiangVien;
import com.example.projectagile.model.MonHoc;
import com.example.projectagile.service.GiangVienService;
import com.example.projectagile.service.MonHocService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller

public class MonHocController {
    private final MonHocService monHocService;
    private final GiangVienService giangVienService;

    public MonHocController(MonHocService monHocService, GiangVienService giangVienService) {
        this.monHocService = monHocService;
        this.giangVienService = giangVienService;
    }

    @GetMapping("/mon-hoc")
    public String MonHoc(
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size
    ) {
        Page<MonHoc> monHocPage = monHocService.getpage(page, size);
        List<GiangVien> giangVienList = giangVienService.getAllGV();

        model.addAttribute("monHocPage", monHocPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", monHocPage.getTotalPages());
        model.addAttribute("giangVienList", giangVienList);
        model.addAttribute("monHoc", new MonHoc());
        model.addAttribute("nameFile", "monHoc/MonHoc"); // Dùng trong layout.html

        return "layout";
    }
    @PostMapping("/MonHoc/Add")
    public String MonHocAdd(
            @Valid @ModelAttribute("monHoc") MonHoc monHoc,
            BindingResult bindingResult,
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size
    ) {
        if (bindingResult.hasErrors()) {
            // Nếu có lỗi, load lại dữ liệu và quay lại view
            Page<MonHoc> monHocPage = monHocService.getpage(page, size);
            List<GiangVien> giangVienList = giangVienService.getAllGV();

            model.addAttribute("monHocPage", monHocPage);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", monHocPage.getTotalPages());
            model.addAttribute("giangVienList", giangVienList);
            model.addAttribute("nameFile", "monHoc/MonHoc");

            return "layout";
        }

        monHocService.save(monHoc);
        return "redirect:/mon-hoc";
    }
}
