package com.example.projectagile.service;

import com.example.projectagile.dto.SinhVienDTO;
import com.example.projectagile.model.SinhVien;
import com.example.projectagile.repository.SinhVienRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SinhVienService implements ISinhVienService {

    private final SinhVienRepository sinhVienRepository;

    @Override
    public List<SinhVienDTO> getAllSinhVien(Long idGiangVien) {
        return sinhVienRepository.getAllSinhVien(idGiangVien);
    }

    @Override
    public List<SinhVien> searchByLopAndKhoaHoc(Long idLop, Long idKhoaHoc) {
        return sinhVienRepository.findByLop_IdLopAndLop_KhoaHoc_IdKhoaHoc(idLop, idKhoaHoc);
    }
}
