package com.example.projectagile.service;

import com.example.projectagile.model.GiangVien;
import com.example.projectagile.repository.GiangVienRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GiangVienService implements IGiangVienService {

    private final GiangVienRepository giangVienRepository;

    @Override
    public Long getIdByUsername(String username) {
        GiangVien giangVien = giangVienRepository.findByUsername(username);
        return giangVien.getIdGiangVien();
    }
    public List<GiangVien> getAllGV() {
        return giangVienRepository.findAll();
    }
}
