package com.example.projectagile.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Diem")
public class Diem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_diem")
    private Long idDiem;

    @NotNull(message = "Sinh viên không được để trống")
    @ManyToOne
    @JoinColumn(name = "id_sinhvien")
    private SinhVien sinhVien;

    @NotNull(message = "Môn học không được để trống")
    @ManyToOne
    @JoinColumn(name = "id_monhoc")
    private MonHoc monHoc;

    @NotNull(message = "Điểm số không được để trống")
    @DecimalMin(value = "0.0", inclusive = true, message = "Điểm phải lớn hơn hoặc bằng 0")
    @DecimalMax(value = "10.0", inclusive = true, message = "Điểm phải nhỏ hơn hoặc bằng 10")
    @Column(name = "diem_so")
    private Double diemSo;
}
