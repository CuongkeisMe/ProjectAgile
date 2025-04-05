package com.example.projectagile.service;

import com.example.projectagile.dto.DiemDTO;
import com.example.projectagile.repository.DiemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiemService implements IDiemService {

    private final DiemRepository diemRepository;

    @Override
    public List<DiemDTO> getAllDiem(Long idGiangVien, String tenSinhVien, Long idMonHoc) {
        return diemRepository.getDiemByGiangVien(idGiangVien, tenSinhVien, idMonHoc);
    }
}
