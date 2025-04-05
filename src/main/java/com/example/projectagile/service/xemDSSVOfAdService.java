package com.example.projectagile.service;

import com.example.projectagile.model.SinhVien;
import com.example.projectagile.repository.SinhVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class xemDSSVOfAdService {
    private final SinhVienRepository sinhVienRepository;

    public xemDSSVOfAdService(SinhVienRepository sinhVienRepository) {
        this.sinhVienRepository = sinhVienRepository;
    }

    public List<SinhVien> getAllSV() {
        return sinhVienRepository.findAll();
    }
}
