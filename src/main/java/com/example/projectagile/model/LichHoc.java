package com.example.projectagile.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "LichHoc")
public class LichHoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lichhoc")
    private Long idLichHoc;

    @ManyToOne
    @JoinColumn(name = "id_monhoc")
    private MonHoc monHoc;

    @Column(name = "ngay_hoc")
    private Date ngayHoc;

    @Column(name = "gio_bat_dau")
    private LocalTime gioBatDau;

    @Column(name = "gio_ket_thuc")
    private LocalTime gioKetThuc;
}