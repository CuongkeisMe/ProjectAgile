package com.example.projectagile.controller;

import com.example.projectagile.dto.LichDayDTO;
import com.example.projectagile.dto.LichHocSinhVienDTO;
import com.example.projectagile.repository.SinhVienRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/lich-hoc")
public class LichHocController {

    @Autowired
    private SinhVienRepository sinhVienRepository;

    @GetMapping("")
    public String getLichHocAllSinhVien(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "15") int size,
                                        Model model) {

        Pageable pageable = PageRequest.of(page, size);
        Page<LichHocSinhVienDTO> lichHocPage = sinhVienRepository.getAllLichHocSinhVien(pageable);

        model.addAttribute("lichHocPage", lichHocPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", lichHocPage.getTotalPages());
        model.addAttribute("nameFile", "lich/lichhoc");
        return "layout";
    }



}
