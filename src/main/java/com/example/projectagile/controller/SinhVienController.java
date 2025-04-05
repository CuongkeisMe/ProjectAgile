package com.example.projectagile.controller;

import com.example.projectagile.dto.SinhVienDTO;
import com.example.projectagile.service.GiangVienService;
import com.example.projectagile.service.KhoaHocService;
import com.example.projectagile.service.LopService;
import com.example.projectagile.service.SinhVienService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class SinhVienController {
    private final SinhVienService sinhVienService;
    private final GiangVienService giangVienService;
    private final KhoaHocService khoaHocService;
    private final LopService lopService;

    @GetMapping("/sinh-vien")
    public String getStudentsByLoggedInGiangVien(
            Principal principal,
            @RequestParam(required = false) String maSinhVien,
            @RequestParam(required = false) String tenSinhVien,
            @RequestParam(required = false) Long idKhoaHoc,
            @RequestParam(required = false) Long idLop,
            Model model) {
        // Lấy username của giảng viên đã đăng nhập
        String username = principal.getName();
        // Dùng username để truy vấn ID của giảng viên
        Long idGiangVien = giangVienService.getIdByUsername(username);

        // Debug ID giảng viên
        System.out.println("idGiangVien = " + idGiangVien);

        // Lấy danh sách sinh viên theo điều kiện tìm kiếm (hoặc tất cả nếu không có điều kiện)
        List<SinhVienDTO> sinhVienList = sinhVienService.getAllSinhVien(idGiangVien, maSinhVien, tenSinhVien, idKhoaHoc, idLop);

        // Thêm dữ liệu vào Model
        model.addAttribute("listSinhVien", sinhVienList);
        model.addAttribute("listKhoaHoc", khoaHocService.getAllKhoaHoc()); // Lấy danh sách khoa học
        model.addAttribute("listLop", lopService.getAllLop()); // Lấy danh sách lớp học

        // Truyền lại giá trị tìm kiếm vào model
        model.addAttribute("maSinhVien", maSinhVien);
        model.addAttribute("tenSinhVien", tenSinhVien);
        model.addAttribute("idKhoaHoc", idKhoaHoc);
        model.addAttribute("idLop", idLop);

        // Xác định file giao diện
        model.addAttribute("nameFile", "sinhvien/sinhvien");
        return "layout"; // Trả về layout chính
    }

}
