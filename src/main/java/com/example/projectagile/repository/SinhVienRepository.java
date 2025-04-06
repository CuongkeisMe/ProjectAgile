package com.example.projectagile.repository;

import com.example.projectagile.dto.SinhVienDTO;
import com.example.projectagile.model.SinhVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SinhVienRepository extends JpaRepository<SinhVien, Long> {
    // cuong
    @Query("SELECT new com.example.projectagile.dto.SinhVienDTO(sv.idSinhVien, sv.maSinhVien, sv.hoTen, l.tenLop, kh.tenKhoaHoc) "
            + "FROM SinhVien sv "
            + "JOIN Lop l ON sv.lop.idLop = l.idLop "
            + "JOIN KhoaHoc kh ON l.khoaHoc.idKhoaHoc = kh.idKhoaHoc "
            + "JOIN Diem d ON sv.idSinhVien = d.sinhVien.idSinhVien "
            + "JOIN MonHoc mh ON d.monHoc.idMonHoc = mh.idMonHoc "
            + "WHERE mh.giangVien.idGiangVien = :idGiangVien "
            + "AND (:maSinhVien IS NULL OR sv.maSinhVien LIKE %:maSinhVien%) "
            + "AND (:tenSinhVien IS NULL OR sv.hoTen LIKE %:tenSinhVien%) "
            + "AND (:idKhoaHoc IS NULL OR kh.idKhoaHoc = :idKhoaHoc) "
            + "AND (:idLop IS NULL OR l.idLop = :idLop)")
    List<SinhVienDTO> getAllSinhVienAndSearch(
            @Param("idGiangVien") Long idGiangVien,
            @Param("maSinhVien") String maSinhVien,
            @Param("tenSinhVien") String tenSinhVien,
            @Param("idKhoaHoc") Long idKhoaHoc,
            @Param("idLop") Long idLop);

    @Query("SELECT new com.example.projectagile.dto.SinhVienDTO(sv.idSinhVien, sv.maSinhVien, sv.hoTen, l.tenLop, kh.tenKhoaHoc) " +
            "FROM SinhVien sv " +
            "JOIN Lop l ON sv.lop.idLop = l.idLop " +
            "JOIN KhoaHoc kh ON l.khoaHoc.idKhoaHoc = kh.idKhoaHoc " +
            "LEFT JOIN Diem d ON sv.idSinhVien = d.sinhVien.idSinhVien " +
            "LEFT JOIN MonHoc mh ON d.monHoc.idMonHoc = mh.idMonHoc " +
            "WHERE mh.giangVien.idGiangVien = :idGiangVien OR d.idDiem IS NULL")
    List<SinhVienDTO> getAllSinhVienTheoGiangVien(@Param("idGiangVien") Long idGiangVien);


    // hao
    List<SinhVien> findByLop_IdLopAndLop_KhoaHoc_IdKhoaHoc(Long idLop, Long idKhoaHoc);
    List<SinhVien> findByLopIdLop(Long idLop);
    List<SinhVien> findByLopKhoaHocIdKhoaHoc(Long idKhoaHoc);
    List<SinhVien> findByLopIdLopAndLopKhoaHocIdKhoaHoc(Long idLop, Long idKhoaHoc);
}
