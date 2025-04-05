package com.example.projectagile.service;

import com.example.projectagile.dto.DiemDTO;
import com.example.projectagile.model.Diem;

import java.util.List;

public interface IDiemService {
    List<DiemDTO> getAllDiem(Long idGiangVien, String tenSinhVien, Long idMonHoc);
}
