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
@Table(name = "LichGiangDay")

public class LichGiangDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lich")
    private Long idLich;

    @ManyToOne
    @JoinColumn(name = "id_giangvien")
    private GiangVien giangVien;

    @ManyToOne
    @JoinColumn(name = "id_monhoc")
    private MonHoc monHoc;

    @Column(name = "ngay_day")
    private Date ngayDay;
}