package com.example.projectagile.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

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

    @NotBlank(message = "Mã sinh viên không được để trống")
    @Column(name = "ma_sinhvien")
    private String maSinhVien;

    @NotBlank(message = "Họ tên không được để trống")
    @Column(name = "ho_ten")
    private String hoTen;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^\\d{10}$", message = "Số điện thoại phải có 10 chữ số")
    @Column(name = "so_dien_thoai")
    private String soDienThoai;

    @ManyToOne
    @JoinColumn(name = "id_lop")
    private Lop lop;
}
