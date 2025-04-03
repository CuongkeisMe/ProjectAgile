package com.example.projectagile.service;

import com.example.projectagile.model.SinhVien;
import com.example.projectagile.repository.SinhVienRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SinhVienService {
    private final SinhVienRepository sinhVienRepository;

    public List<SinhVien> getSinhVienByGiangVien(Long giangVienId) {
        return sinhVienRepository.findByGiangVienId(giangVienId);
    }
}
