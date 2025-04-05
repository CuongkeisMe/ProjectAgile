package com.example.projectagile.controller;

import com.example.projectagile.model.GiangVien;
import com.example.projectagile.model.User;
import com.example.projectagile.repository.UserRepository;
import com.example.projectagile.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserRepository userRepository;

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
        model.addAttribute("user", user);
        return "layout";
    }
}