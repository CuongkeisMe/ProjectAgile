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
@Table(name = "Lop")

public class Lop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lop")
    private Long idLop;
    @Column(name = "ten_lop")
    private String tenLop;
    @ManyToOne
    @JoinColumn(name = "id_khoahoc")
    private KhoaHoc khoaHoc;
}
