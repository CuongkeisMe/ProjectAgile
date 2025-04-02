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
@Table(name = "MonHoc")

public class MonHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_monhoc")
    private Long idMonHoc;
    @Column(name = "ten_monhoc")
    private String tenMonHoc;
    @ManyToOne
    @JoinColumn(name = "id_giangvien")
    private GiangVien giangVien;
}
