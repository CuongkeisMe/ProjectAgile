package com.example.projectagile.controller;

import com.example.projectagile.model.GiangVien;
import com.example.projectagile.model.MonHoc;
import com.example.projectagile.service.GiangVienService;
import com.example.projectagile.service.MonHocService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller

public class MonHocController {
    private final MonHocService monHocService;
    private final GiangVienService giangVienService;

    public MonHocController(MonHocService monHocService, GiangVienService giangVienService) {
        this.monHocService = monHocService;
        this.giangVienService = giangVienService;
    }
    @GetMapping("/MonHoc")
    public String MonHoc(Model model) {
        List<MonHoc> list = monHocService.getAllMH();
        model.addAttribute("list", list);
        List<GiangVien> giangVienList = giangVienService.getAllGV();
        model.addAttribute("giangVienList", giangVienList);
        model.addAttribute("monHoc", new MonHoc());
        model.addAttribute("nameFile", "monHoc/MonHoc");
        return "layout";

    }
    @PostMapping("/MonHoc/Add")
    public String MonHocAdd(@Valid @ModelAttribute("monHoc") MonHoc monHoc, Model model) {
        monHocService.save(monHoc);
        return "redirect:/MonHoc";
    }
}
