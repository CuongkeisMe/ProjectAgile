package com.example.projectagile.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class SinhVienDTO {
    @Id
    private Long idSinhVien;
    private String maSinhVien;
    private String hoTen;
    private String tenLop;
    private String tenKhoaHoc;
}
