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

public class LichDayDTO {
    @Id
    private String tenMonHoc;
    private java.sql.Timestamp ngayHoc; // hoặc java.util.Date nếu cần
    private java.time.LocalTime gioBatDau;
    private java.time.LocalTime gioKetThuc;
    private String maLop;
    private String tenLop;
}
