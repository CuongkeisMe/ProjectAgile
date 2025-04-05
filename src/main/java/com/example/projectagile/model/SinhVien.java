package com.example.projectagile.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "SinhVien")

public class SinhVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sinhvien")
    private Long idSinhVien;
    @Column(name = "ho_ten")
    private String hoTen;
    @Column(name = "email")
    private String email;
    @Column(name = "so_dien_thoai")
    private String soDienThoai;
    @ManyToOne
    @JoinColumn(name = "id_lop")
    private Lop lop;
    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;
}
