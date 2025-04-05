package com.example.projectagile.service;

import com.example.projectagile.dto.SinhVienDTO;
import com.example.projectagile.model.SinhVien;

import java.util.List;

public interface ISinhVienService {
    List<SinhVienDTO> getAllSinhVien(Long idGiangVien, String maSinhVien, String tenSinhVien, Long idkhoaHoc, Long idLop);
    List<SinhVien> searchByLopAndKhoaHoc(Long idLop, Long idKhoaHoc);
}
