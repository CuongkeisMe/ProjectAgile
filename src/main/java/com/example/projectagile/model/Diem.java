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
@Table(name = "Diem")

public class Diem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_diem")
    private Long idDiem;

    @ManyToOne
    @JoinColumn(name = "id_sinhvien")
    private SinhVien sinhVien;

    @ManyToOne
    @JoinColumn(name = "id_monhoc")
    private MonHoc monHoc;

    @Column(name = "diem_so")
    private Double diemSo;
}