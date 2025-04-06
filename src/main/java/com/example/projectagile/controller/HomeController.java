package com.example.projectagile.controller;

import com.example.projectagile.model.GiangVien;
import com.example.projectagile.model.User;
import com.example.projectagile.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserRepository userRepository;
    private final KhoaHocRepository khoaHocRepository;
    private final GiangVienRepository giangVienRepository;
    private final LopRepository lopRepository;
    private final SinhVienRepository sinhVienRepository;
    private final MonHocRepository monHocRepository;

//    @ModelAttribute("user")
//    public User addUserToModel(HttpSession session) {
//        String username = (String) session.getAttribute("username");
//        return userRepository.findByUsername(username).orElse(null);
//    }

    @GetMapping("/")
    public String home(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userRepository.findByUsername(userDetails.getUsername()).orElse(null);
        // Lay giang vien theo user
        if (user != null && user.getGiangVien() != null) {
            GiangVien giangVien = user.getGiangVien();
            model.addAttribute("giangVien", giangVien);
        }
        if ("admin".equals(user.getRole())) {
            // Admin
            model.addAttribute("tongKhoaHoc", khoaHocRepository.findAll().size());
            model.addAttribute("tongMonHoc", monHocRepository.findAll().size());
            model.addAttribute("tongSinhVien", sinhVienRepository.findAll().size());
            model.addAttribute("tongLopHoc", lopRepository.findAll().size());
            model.addAttribute("tongGiangVien", giangVienRepository.findAll().size());
            model.addAttribute("nameFile", "home");
        } else {
            // Giảng viên: Lấy ID giảng viên từ User
            model.addAttribute("nameFile", "home_teacher");
        }
        model.addAttribute("user", user);
        return "layout";
    }

}