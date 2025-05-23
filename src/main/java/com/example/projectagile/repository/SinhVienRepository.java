package com.example.projectagile.repository;

import com.example.projectagile.dto.LichHocSinhVienDTO;
import com.example.projectagile.dto.SinhVienDTO;
import com.example.projectagile.model.SinhVien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SinhVienRepository extends JpaRepository<SinhVien, Long> {
    // cuong
    @Query("SELECT DISTINCT new com.example.projectagile.dto.SinhVienDTO(sv.idSinhVien, sv.maSinhVien, sv.hoTen, l.tenLop, kh.tenKhoaHoc) " +
            "FROM SinhVien sv " +
            "JOIN Lop l ON sv.lop.idLop = l.idLop " +
            "JOIN KhoaHoc kh ON l.khoaHoc.idKhoaHoc = kh.idKhoaHoc " +
            "LEFT JOIN Diem d ON sv.idSinhVien = d.sinhVien.idSinhVien " +
            "LEFT JOIN MonHoc mh ON d.monHoc.idMonHoc = mh.idMonHoc " +
            "WHERE (:idGiangVien IS NULL OR mh.giangVien.idGiangVien = :idGiangVien) " +
            "AND (:maSinhVien IS NULL OR sv.maSinhVien LIKE %:maSinhVien%) " +
            "AND (:tenSinhVien IS NULL OR sv.hoTen LIKE %:tenSinhVien%) " +
            "AND (:idKhoaHoc IS NULL OR kh.idKhoaHoc = :idKhoaHoc) " +
            "AND (:idLop IS NULL OR l.idLop = :idLop)")
    List<SinhVienDTO> getAllSinhVienAndSearch(
            @Param("idGiangVien") Long idGiangVien,
            @Param("maSinhVien") String maSinhVien,
            @Param("tenSinhVien") String tenSinhVien,
            @Param("idKhoaHoc") Long idKhoaHoc,
            @Param("idLop") Long idLop);

    @Query("SELECT DISTINCT new com.example.projectagile.dto.SinhVienDTO(sv.idSinhVien, sv.maSinhVien, sv.hoTen, l.tenLop, kh.tenKhoaHoc) " +
            "FROM SinhVien sv " +
            "JOIN Lop l ON sv.lop.idLop = l.idLop " +
            "JOIN KhoaHoc kh ON l.khoaHoc.idKhoaHoc = kh.idKhoaHoc " +
            "LEFT JOIN Diem d ON sv.idSinhVien = d.sinhVien.idSinhVien " +
            "LEFT JOIN MonHoc mh ON d.monHoc.idMonHoc = mh.idMonHoc " +
            "WHERE (:idGiangVien IS NULL OR mh.giangVien.idGiangVien = :idGiangVien) OR d.idDiem IS NULL")
    List<SinhVienDTO> getAllSinhVienTheoGiangVien(@Param("idGiangVien") Long idGiangVien);


    // hao
    List<SinhVien> findByLop_IdLopAndLop_KhoaHoc_IdKhoaHoc(Long idLop, Long idKhoaHoc);

    List<SinhVien> findByLopIdLop(Long idLop);

    List<SinhVien> findByLopKhoaHocIdKhoaHoc(Long idKhoaHoc);

    List<SinhVien> findByLopIdLopAndLopKhoaHocIdKhoaHoc(Long idLop, Long idKhoaHoc);

    @Query("SELECT new com.example.projectagile.dto.LichHocSinhVienDTO(sv.maSinhVien, sv.hoTen, mh.tenMonHoc, lh.ngayHoc, lh.gioBatDau, lh.gioKetThuc) FROM SinhVien sv JOIN Lop l ON sv.lop.idLop = l.idLop JOIN KhoaHoc kh ON l.khoaHoc.idKhoaHoc = kh.idKhoaHoc JOIN MonHoc mh ON mh.giangVien.idGiangVien IS NOT NULL JOIN LichHoc lh ON lh.monHoc.idMonHoc = mh.idMonHoc")
    Page<LichHocSinhVienDTO> getAllLichHocSinhVien(Pageable pageable);

}
