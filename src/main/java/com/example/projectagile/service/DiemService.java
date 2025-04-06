package com.example.projectagile.service;

import com.example.projectagile.dto.DiemDTO;
import com.example.projectagile.model.Diem;
import com.example.projectagile.model.MonHoc;
import com.example.projectagile.model.SinhVien;
import com.example.projectagile.repository.DiemRepository;
import com.example.projectagile.repository.MonHocRepository;
import com.example.projectagile.repository.SinhVienRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiemService implements IDiemService {

    private final DiemRepository diemRepository;
    private final SinhVienRepository sinhVienRepository;
    private final MonHocRepository monHocRepository;

    @Override
    public Page<DiemDTO> getAllDiem(Long idGiangVien, String tenSinhVien, Long idMonHoc, Pageable pageable) {
        return diemRepository.getDiemByGiangVien(idGiangVien, tenSinhVien, idMonHoc, pageable);
    }

    @Override
    public Diem addDiem(Diem diem) {
        return diemRepository.save(diem);
    }

    @Override
    public Diem getDiemById(Long id) {
        return diemRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Diem updateDiem(Diem diem, Long idDiem) {
        // Tìm kiếm đối tượng Diem để cập nhật
        Diem existingDiem = diemRepository.findById(idDiem)
                .orElseThrow(() -> new RuntimeException("Điểm không tồn tại!"));

        // Cập nhật thông tin
        existingDiem.setDiemSo(diem.getDiemSo());

        // Lưu đối tượng đã được cập nhật
        return diemRepository.save(existingDiem);
    }


    @Override
    public void deleteDiem(Long idDiem) {
        diemRepository.deleteById(idDiem);
    }
}
