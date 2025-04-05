package com.example.projectagile.controller;

import com.example.projectagile.dto.DiemDTO;
import com.example.projectagile.service.DiemService;
import com.example.projectagile.service.GiangVienService;
import com.example.projectagile.service.MonHocService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class DiemController {

    private final GiangVienService giangVienService;
    private final DiemService diemService;
    private final MonHocService monHocService;

    @GetMapping("/diem")
    public String getDiemByLoggedInGiangVien(
            Principal principal,
            @RequestParam(required = false) String tenSinhVien,
            @RequestParam(required = false) Long idMonHoc,
            Model model) {
        // Lấy username của giảng viên đăng nhập
        String username = principal.getName();
        Long idGiangVien = giangVienService.getIdByUsername(username);

        // Lấy danh sách điểm của sinh viên dựa trên điều kiện tìm kiếm (nếu có)
        List<DiemDTO> listDiem = diemService.getAllDiem(idGiangVien, tenSinhVien, idMonHoc);

        // Thêm dữ liệu vào Model
        model.addAttribute("listDiem", listDiem);
        model.addAttribute("listMonHoc", monHocService.getAllMonHoc()); // Lấy danh sách môn học
        model.addAttribute("tenSinhVien", tenSinhVien); // Giữ giá trị đã nhập cho ô tìm kiếm tên sinh viên
        model.addAttribute("idMonHoc", idMonHoc); // Giữ lựa chọn của dropdown môn học
        model.addAttribute("nameFile", "diem/diem");
        return "layout"; // Trả về layout chính
    }

}
