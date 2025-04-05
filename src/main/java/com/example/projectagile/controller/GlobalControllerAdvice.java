package com.example.projectagile.controller;

import com.example.projectagile.dto.UserLoginDTO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute("user")
    public UserLoginDTO globalUser(Principal principal) {
        if (principal != null) {
            return new UserLoginDTO(principal.getName(), principal.getName());
        }
        return null;
    }
}