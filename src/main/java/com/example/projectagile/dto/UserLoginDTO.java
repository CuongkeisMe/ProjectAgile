package com.example.projectagile.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UserLoginDTO {
    @NotBlank(message = "User name cannot be blank")
    private String username;
    @NotBlank(message = "Password cannot be blank")
    private String password;
}
