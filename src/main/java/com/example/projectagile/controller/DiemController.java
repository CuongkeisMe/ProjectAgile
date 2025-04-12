package com.example.projectagile.controller;

import com.example.projectagile.dto.DiemDTO;
import com.example.projectagile.dto.SinhVienDTO;
import com.example.projectagile.model.Diem;
import com.example.projectagile.model.SinhVien;
import com.example.projectagile.model.User;
import com.example.projectagile.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    private final UserService userService;

    @GetMapping("")
    public String getDiemByLoggedInUser(
            Principal principal,
            @RequestParam(required = false) String tenSinhVien,
            @RequestParam(required = false) Long idMonHoc,
            @RequestParam(defaultValue = "0") int p,
            Model model) {
        // Lấy username của người dùng đã đăng nhập
        String username = principal.getName();

        // Lấy thông tin người dùng (admin hoặc giảng viên)
        User user = userService.getUserByUsername(username);

        // Phân trang
        Pageable pageable = PageRequest.of(p, 8);
        Page<DiemDTO> pages;

        // Kiểm tra vai trò của người dùng
        if ("admin".equals(user.getRole())) {
            // Admin: Lấy toàn bộ danh sách điểm
            pages = diemService.getAllDiem(null, tenSinhVien, idMonHoc, pageable);
            model.addAttribute("listMonHoc", monHocService.findAllByGiangVienId(null)); // Admin: Lấy danh sách tất cả môn học
        } else if ("teacher".equals(user.getRole())) {
            // Giảng viên: Lấy ID giảng viên từ User
            Long idGiangVien = giangVienService.getIdByUsername(username);

            // Debug ID giảng viên
            System.out.println("idGiangVien = " + idGiangVien);

            // Lấy danh sách điểm theo ID giảng viên
            pages = diemService.getAllDiem(idGiangVien, tenSinhVien, idMonHoc, pageable);
            model.addAttribute("listMonHoc", monHocService.findAllByGiangVienId(idGiangVien)); // Lấy môn học của giảng viên
        } else {
            throw new RuntimeException("Người dùng không có quyền truy cập!");
        }

        // Thêm dữ liệu vào Model
        model.addAttribute("listDiem", pages);
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
        String username = principal.getName();
        User user = userService.getUserByUsername(username); // Lấy user từ username

        model.addAttribute("diem", new Diem());

        if ("teacher".equals(user.getRole())) {
            Long idGiangVien = giangVienService.getIdByUsername(username);
            model.addAttribute("listSV", sinhVienService.getAllSinhVienTheoGiangVien(idGiangVien));
            model.addAttribute("listMonHoc", monHocService.findAllByGiangVienId(idGiangVien));
        } else if ("admin".equals(user.getRole())) {
            model.addAttribute("listSV", sinhVienService.getAllSinhVienTheoGiangVien(null));
            model.addAttribute("listMonHoc", monHocService.getAllMH());
        }

        model.addAttribute("nameFile", "diem/add_diem");
        return "layout";
    }


    @PostMapping("/add")
    public String addDiem(@Valid @ModelAttribute("diem") Diem diem, BindingResult result, Model model, RedirectAttributes redirectAttributes, Principal principal) {
        // Lấy thông tin người dùng từ principal (tên đăng nhập)
        String username = principal.getName();
        User user = userService.getUserByUsername(username); // Lấy thông tin người dùng từ username
        Long idGiangVien = null;

        // Kiểm tra vai trò của người dùng
        if ("teacher".equals(user.getRole())) {
            // Nếu là giảng viên, lấy ID giảng viên từ tên đăng nhập
            idGiangVien = giangVienService.getIdByUsername(username);
        }

        // Kiểm tra nếu có lỗi validation
        if (result.hasErrors()) {
            // Nếu có lỗi validation, hiển thị lại form và truyền thông tin danh sách sinh viên và môn học
            if ("admin".equals(user.getRole())) {
                // Nếu là admin, lấy tất cả sinh viên
                model.addAttribute("listSV", sinhVienService.getAllSinhVienTheoGiangVien(null));
            } else {
                // Nếu là giảng viên, chỉ lấy sinh viên của giảng viên đó
                model.addAttribute("listSV", sinhVienService.getAllSinhVienTheoGiangVien(idGiangVien));
            }
            model.addAttribute("diem", diem);
            // Truyền danh sách môn học
            model.addAttribute("listMonHoc", monHocService.getAllMH());
            model.addAttribute("nameFile", "diem/add_diem");
            return "layout"; // Trả lại giao diện form nhập điểm
        }

        // Thêm điểm vào database
        diemService.addDiem(diem);
        redirectAttributes.addFlashAttribute("message", "Thêm thành công!");
        return "redirect:/diem"; // Chuyển hướng về trang điểm sau khi thêm
    }

    @GetMapping("/delete/{id}")
    public String deleteDiem(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        diemService.deleteDiem(id);
        redirectAttributes.addFlashAttribute("message", "Xóa thành công!");
        return "redirect:/diem";
    }
    
    @GetMapping("/update/{id}")
    public String detailDiem(@PathVariable("id") Long id, Model model, Principal principal) {
        Diem diem = diemService.getDiemById(id);
        model.addAttribute("diem", diem);

        // Lấy username đang đăng nhập
        String username = principal.getName();
        User user = userService.getUserByUsername(username);

        if ("admin".equals(user.getRole())) {
            model.addAttribute("listSV", sinhVienService.getAllSinhVienTheoGiangVien(null));
            model.addAttribute("listMonHoc", monHocService.getAllMH());
        } else if ("teacher".equals(user.getRole())) {
            Long idGiangVien = giangVienService.getIdByUsername(username);
            model.addAttribute("listSV", sinhVienService.getAllSinhVienTheoGiangVien(idGiangVien));
            model.addAttribute("listMonHoc", monHocService.findAllByGiangVienId(idGiangVien));
        }

        model.addAttribute("nameFile", "diem/update_diem");
        return "layout";
    }

    @PostMapping("/update/{id}")
    public String updateDiem(@PathVariable("id") Long id,
                             @Valid @ModelAttribute("diem") Diem diem,
                             BindingResult result,
                             Model model,
                             Principal principal,
                             RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            String username = principal.getName();
            User user = userService.getUserByUsername(username);

            if ("admin".equals(user.getRole())) {
                model.addAttribute("listSV", sinhVienService.getAllSinhVienTheoGiangVien(null));
                model.addAttribute("listMonHoc", monHocService.getAllMH());
            } else if ("teacher".equals(user.getRole())) {
                Long idGiangVien = giangVienService.getIdByUsername(username);
                model.addAttribute("listSV", sinhVienService.getAllSinhVienTheoGiangVien(idGiangVien));
                model.addAttribute("listMonHoc", monHocService.findAllByGiangVienId(idGiangVien));
            }

            model.addAttribute("nameFile", "diem/update_diem");
            return "layout"; // Trả lại form nếu có lỗi
        }

        diemService.updateDiem(diem, id);
        redirectAttributes.addFlashAttribute("message", "Sửa thành công!");
        return "redirect:/diem";
    }



}
