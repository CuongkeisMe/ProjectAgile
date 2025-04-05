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
@Table(name = "GiangVien")

public class GiangVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_giangvien")
    private Long idGiangVien;
    @Column(name = "ma_giangvien")
    private String maGiangVien;
    @Column(name = "ho_ten")
    private String hoTen;
    @Column(name = "email")
    private String email;
    @Column(name = "so_dien_thoai")
    private String soDienThoai;
    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;
}
