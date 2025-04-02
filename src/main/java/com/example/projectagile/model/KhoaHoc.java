package com.example.projectagile.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "KhoaHoc")

public class KhoaHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_khoahoc")
    private Long idKhoaHoc;
    @Column(name = "ten_khoahoc")
    private String tenKhoaHoc;
    @Temporal(TemporalType.DATE)
    @Column(name = "thoi_gian_bat_dau")
    private Date thoiGianBatDau;
    @Temporal(TemporalType.DATE)
    @Column(name = "thoi_gian_ket_thuc")
    private Date thoiGianKetThuc;
}
