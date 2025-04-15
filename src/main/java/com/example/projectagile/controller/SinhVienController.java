package com.example.projectagile.controller;

import com.example.projectagile.dto.SinhVienDTO;
import com.example.projectagile.model.SinhVien;
import com.example.projectagile.model.User;
import com.example.projectagile.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/sinh-vien")
public class SinhVienController {

    private final SinhVienService sinhVienService;
    private final GiangVienService giangVienService;
    private final KhoaHocService khoaHocService;
    private final LopService lopService;
    private final UserService userService;

    @GetMapping("")
    public String getStudentsByLoggedInUser(
            Principal principal,
            @RequestParam(required = false) String maSinhVien,
            @RequestParam(required = false) String tenSinhVien,
            @RequestParam(required = false) Long idKhoaHoc,
            @RequestParam(required = false) Long idLop,
            Model model) {
        // Lấy username của người dùng đã đăng nhập
        String username = principal.getName();

        // Lấy thông tin người dùng (admin hoặc giảng viên)
        User user = userService.getUserByUsername(username);

        List<SinhVienDTO> sinhVienList;

        // Kiểm tra vai trò của người dùng
        if ("admin".equals(user.getRole())) {
            // Admin: Lấy tất cả sinh viên trong hệ thống
            sinhVienList = sinhVienService.getAllSinhVienAndSearch(null, maSinhVien, tenSinhVien, idKhoaHoc, idLop);
        } else if ("teacher".equals(user.getRole())) {
            // Giảng viên: Lấy ID giảng viên từ User
            Long idGiangVien = giangVienService.getIdByUsername(username);

            // Debug ID giảng viên
            System.out.println("idGiangVien = " + idGiangVien);

            // Lấy danh sách sinh viên liên quan đến giảng viên
            sinhVienList = sinhVienService.getAllSinhVienAndSearch(idGiangVien, maSinhVien, tenSinhVien, idKhoaHoc, idLop);
        } else {
            throw new RuntimeException("Người dùng không có quyền truy cập!");
        }

        // Thêm dữ liệu vào Model
        model.addAttribute("listSinhVien", sinhVienList);
        model.addAttribute("listKhoaHoc", khoaHocService.getAllKhoaHoc()); // Lấy danh sách khóa học
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

    @GetMapping("/add")
    public String formAdd(Model model) {
        model.addAttribute("sinhVien", new SinhVien());
        model.addAttribute("listLop", lopService.getAllLop());
        model.addAttribute("nameFile", "sinhvien/add_sinhvien");
        return "layout";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("sinhVien") SinhVien sinhVien,
                       BindingResult result,
                       Model model) {
        if (result.hasErrors()) {
            model.addAttribute("listLop", lopService.getAllLop());
            model.addAttribute("nameFile", "sinhvien/add_sinhvien"); // hoặc form sửa nếu là edit
            return "layout";
        }

        sinhVienService.addSV(sinhVien);
        return "redirect:/sinh-vien";
    }


    @GetMapping("/update/{id}")
    public String formUpdate(@PathVariable Long id, Model model) {
        model.addAttribute("sinhVien", sinhVienService.getSVById(id));
        model.addAttribute("listLop", lopService.getAllLop());
        model.addAttribute("nameFile", "sinhvien/update_sinhVien");
        return "layout";
    }

    @PostMapping("/update/{id}")
    public String updateSV(@Valid @ModelAttribute("sinhVien") SinhVien sinhVien,
                           BindingResult result,
                           Model model) {
        if (result.hasErrors()) {
            model.addAttribute("listLop", lopService.getAllLop());
            model.addAttribute("nameFile", "sinhvien/update_sinhvien"); // hoặc form update bạn đang dùng
            return "layout"; // quay về form edit nếu có lỗi
        }
        sinhVienService.updateSV(sinhVien);
        return "redirect:/sinh-vien";
    }


}
