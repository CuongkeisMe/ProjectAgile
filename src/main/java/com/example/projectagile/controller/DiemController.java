package com.example.projectagile.controller;

import com.example.projectagile.dto.DiemDTO;
import com.example.projectagile.dto.SinhVienDTO;
import com.example.projectagile.model.Diem;
import com.example.projectagile.model.SinhVien;
import com.example.projectagile.service.DiemService;
import com.example.projectagile.service.GiangVienService;
import com.example.projectagile.service.MonHocService;
import com.example.projectagile.service.SinhVienService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/diem")
public class DiemController {

    private final GiangVienService giangVienService;
    private final DiemService diemService;
    private final MonHocService monHocService;
    private final SinhVienService sinhVienService;

    @GetMapping("")
    public String getDiemByLoggedInGiangVien(
            Principal principal,
            @RequestParam(required = false) String tenSinhVien,
            @RequestParam(required = false) Long idMonHoc,
            @RequestParam(defaultValue = "0") int p,
            Model model) {
        // Lấy username của giảng viên đăng nhập
        String username = principal.getName();
        Long idGiangVien = giangVienService.getIdByUsername(username);
        // Phan trang
        Pageable pageable = PageRequest.of(p, 8);
        // Lấy danh sách điểm của sinh viên dựa trên điều kiện tìm kiếm (nếu có)
        Page<DiemDTO> pages = diemService.getAllDiem(idGiangVien, tenSinhVien, idMonHoc, pageable);

        // Thêm dữ liệu vào Model
        model.addAttribute("listDiem", pages);
        model.addAttribute("listMonHoc", monHocService.findAllByGiangVienId(idGiangVien)); // Lấy danh sách môn học
        model.addAttribute("tenSinhVien", tenSinhVien); // Giữ giá trị đã nhập cho ô tìm kiếm tên sinh viên
        model.addAttribute("idMonHoc", idMonHoc); // Giữ lựa chọn của dropdown môn học
        model.addAttribute("pages", pages);
        model.addAttribute("currentPage", p);
        model.addAttribute("totalPages", pages.getTotalPages());
        model.addAttribute("nameFile", "diem/diem");
        return "layout"; // Trả về layout chính
    }

    @GetMapping("/add")
    public String formAdd(Principal principal, Model model) {
        // Lấy username của giảng viên đăng nhập
        String username = principal.getName();
        Long idGiangVien = giangVienService.getIdByUsername(username);
        model.addAttribute("diem", new Diem());
        model.addAttribute("listSV", sinhVienService.getAllSinhVienTheoGiangVien(idGiangVien));
        model.addAttribute("listMonHoc", monHocService.findAllByGiangVienId(idGiangVien));
        model.addAttribute("nameFile", "diem/add_diem");
        return "layout";
    }

    @PostMapping("/add")
    public String addDiem(@ModelAttribute("diem") Diem diem, Model model, RedirectAttributes redirectAttributes) {
        diemService.addDiem(diem);
        redirectAttributes.addFlashAttribute("message", "Thêm thành công!");
        return "redirect:/diem";
    }

    @GetMapping("/delete/{id}")
    public String deleteDiem(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        diemService.deleteDiem(id);
        redirectAttributes.addFlashAttribute("message", "Xóa thành công!");
        return "redirect:/diem";
    }

    @GetMapping("/update/{id}")
    public String detailDiem(@PathVariable("id") Long id, Model model) {
        model.addAttribute("diem", diemService.getDiemById(id));
        model.addAttribute("nameFile", "diem/update_diem");
        return "layout";
    }

    @PostMapping("/update/{id}")
    public String updateDiem(@PathVariable("id") Long id, @ModelAttribute("diem") Diem diem, RedirectAttributes redirectAttributes) {
        diemService.updateDiem(diem, id);
        redirectAttributes.addFlashAttribute("message", "Sửa thành công!");
        return "redirect:/diem";
    }

}
