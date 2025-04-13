package com.example.projectagile.controller;

import com.example.projectagile.dto.LichDayDTO;
import com.example.projectagile.model.User;
import com.example.projectagile.repository.LichHocRepository;
import com.example.projectagile.service.GiangVienService;
import com.example.projectagile.service.UserService;
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
    @Autowired
    private UserService userService;

    @GetMapping("")
    public String getLichDayByUser(Model model, Principal principal) {
        String username = principal.getName();

        // Lấy thông tin người dùng
        User user = userService.getUserByUsername(username);

        List<LichDayDTO> lichDayList;

        // Kiểm tra role
        if ("admin".equals(user.getRole())) {
            // Nếu là admin, lấy toàn bộ lịch dạy
            lichDayList = lichHocRepository.getLichDayByGiangVien(null);
        } else if ("teacher".equals(user.getRole())) {
            // Nếu là giảng viên, lấy lịch dạy theo giảng viên
            Long idGiangVien = giangVienService.getIdByUsername(username);
            lichDayList = lichHocRepository.getLichDayByGiangVien(idGiangVien);
        } else {
            throw new RuntimeException("Người dùng không có quyền truy cập!");
        }

        // Truyền dữ liệu ra giao diện
        model.addAttribute("lichDayList", lichDayList);
        model.addAttribute("nameFile", "lich/lich");
        return "layout";
    }

}