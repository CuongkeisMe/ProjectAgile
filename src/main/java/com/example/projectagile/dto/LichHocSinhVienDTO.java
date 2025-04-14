package com.example.projectagile.dto;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
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

public class LichHocSinhVienDTO {
    @Id
    private String maSinhVien;
    private String hoTenSinhVien;
    private String tenMonHoc;
    private Timestamp ngayHoc;
    private LocalTime gioBatDau;
    private LocalTime gioKetThuc;

}
