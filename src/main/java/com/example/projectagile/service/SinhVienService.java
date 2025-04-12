package com.example.projectagile.service;

import com.example.projectagile.dto.SinhVienDTO;
import com.example.projectagile.model.SinhVien;
import com.example.projectagile.repository.SinhVienRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SinhVienService implements ISinhVienService {

    private final SinhVienRepository sinhVienRepository;

    @Override
    public List<SinhVienDTO> getAllSinhVienTheoGiangVien(Long idGiangVien) {
        return sinhVienRepository.getAllSinhVienTheoGiangVien(idGiangVien);
    }

    @Override
    public List<SinhVienDTO> getAllSinhVienAndSearch(Long idGiangVien, String maSinhVien, String tenSinhVien, Long idkhoaHoc, Long idLop) {
        return sinhVienRepository.getAllSinhVienAndSearch(idGiangVien, maSinhVien, tenSinhVien, idkhoaHoc, idLop);
    }

    @Override
    public List<SinhVien> searchByLopAndKhoaHoc(Long idLop, Long idKhoaHoc) {
        return sinhVienRepository.findByLop_IdLopAndLop_KhoaHoc_IdKhoaHoc(idLop, idKhoaHoc);
    }
    public void addSV(SinhVien sinhVien) {
        sinhVienRepository.save(sinhVien);
    }

    public Object getALlSV(@Valid SinhVien sinhVien) {
        return sinhVienRepository.findAll();
    }

    public List<SinhVien> getALlSV1() {
        return sinhVienRepository.findAll();
    }


    public SinhVien getSVById(Long id) {
        return sinhVienRepository.findById(id).get();
    }


    public void updateSV(SinhVien sinhVien) {
        sinhVienRepository.save(sinhVien);
    }
}
