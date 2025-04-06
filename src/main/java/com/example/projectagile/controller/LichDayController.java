package com.example.projectagile.controller;

import com.example.projectagile.dto.LichDayDTO;
import com.example.projectagile.repository.LichHocRepository;
import com.example.projectagile.service.GiangVienService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/lich-day")
@RequiredArgsConstructor
public class LichDayController {

    @Autowired
    private LichHocRepository lichHocRepository;
    private final GiangVienService giangVienService;

    @GetMapping("")
    public String getLichDayByGiangVien(Model model, Principal principal) {
        String username = principal.getName();
        Long idGiangVien = giangVienService.getIdByUsername(username);
        List<LichDayDTO> lichDayList = lichHocRepository.getLichDayByGiangVien(idGiangVien);
        model.addAttribute("lichDayList", lichDayList);
        model.addAttribute("nameFile", "lich/lich");
        return "layout"; // Tên file HTML (giao diện)
    }
}