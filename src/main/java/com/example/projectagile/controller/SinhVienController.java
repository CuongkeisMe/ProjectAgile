package com.example.projectagile.controller;

import com.example.projectagile.dto.SinhVienDTO;
import com.example.projectagile.service.GiangVienService;
import com.example.projectagile.service.SinhVienService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class SinhVienController {
    private final SinhVienService sinhVienService;
    private final GiangVienService giangVienService;

    @GetMapping("/sinh-vien")
    public String getStudentsByLoggedInGiangVien(Principal principal, Model model) {
        // Lấy username của giảng viên đã đăng nhập
        String username = principal.getName();
        // Dùng username để truy vấn ID của giảng viên
        Long idGiangVien = giangVienService.getIdByUsername(username);
        // Lấy danh sách sinh viên do giảng viên dạy
        System.out.println("idGiangVien = " + idGiangVien);
        List<SinhVienDTO> listSinhVien = sinhVienService.getAllSinhVien(idGiangVien);
        model.addAttribute("listSinhVien", listSinhVien);
        model.addAttribute("nameFile", "sinhvien/sinhvien");
        return "layout";
    }
}
