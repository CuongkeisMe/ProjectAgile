package com.example.projectagile.controller;

import com.example.projectagile.dto.UserLoginDTO;
import com.example.projectagile.exception.DataNotFoundException;
import com.example.projectagile.model.User;
import com.example.projectagile.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("userLogin", new UserLoginDTO());
        return "login/login";
    }
    @PostMapping("/login")
    public String login(@ModelAttribute("userLoginDTO") UserLoginDTO userLoginDTO,
                        HttpSession session, Model model,
                        BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                return "login/login";
            }
            User user = userService.login(userLoginDTO.getUsername(), userLoginDTO.getPassword());
            model.addAttribute("message", "Đăng nhập thành công!");
            return "redirect:/";
        } catch (DataNotFoundException | BadCredentialsException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "login/login";
        }
    }
}
