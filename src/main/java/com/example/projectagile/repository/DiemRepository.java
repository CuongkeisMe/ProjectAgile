package com.example.projectagile.repository;

import com.example.projectagile.dto.DiemDTO;
import com.example.projectagile.model.Diem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiemRepository extends JpaRepository<Diem, Long> {
    @Query("SELECT new com.example.projectagile.dto.DiemDTO(d.idDiem, sv.idSinhVien, sv.maSinhVien, sv.hoTen, mh.tenMonHoc, d.diemSo) " +
            "FROM Diem d " +
            "JOIN d.sinhVien sv " +
            "JOIN d.monHoc mh " +
            "WHERE (:idGiangVien IS NULL OR mh.giangVien.idGiangVien = :idGiangVien) " +
            "AND (:tenSinhVien IS NULL OR sv.hoTen LIKE %:tenSinhVien%) " +
            "AND (:idMonHoc IS NULL OR mh.idMonHoc = :idMonHoc)")
    Page<DiemDTO> getDiemByGiangVien(
            @Param("idGiangVien") Long idGiangVien,
            @Param("tenSinhVien") String tenSinhVien,
            @Param("idMonHoc") Long idMonHoc,
            Pageable pageable);

}
