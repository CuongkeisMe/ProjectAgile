package com.example.projectagile.service;

import com.example.projectagile.dto.DiemDTO;
import com.example.projectagile.model.Diem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDiemService {
    Page<DiemDTO> getAllDiem(Long idGiangVien, String tenSinhVien, Long idMonHoc, Pageable pageable);
    Diem addDiem(Diem diem);
    Diem getDiemById(Long id);
    Diem updateDiem(Diem diem, Long idDiem);
    void deleteDiem(Long idDiem);
}
