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
    @Query("SELECT new com.example.projectagile.dto.SinhVienDTO(sv.idSinhVien, sv.maSinhVien, sv.hoTen, l.tenLop, kh.tenKhoaHoc) FROM SinhVien sv JOIN Lop l ON sv.lop.idLop = l.idLop JOIN KhoaHoc kh ON l.khoaHoc.idKhoaHoc = kh.idKhoaHoc JOIN Diem d ON sv.idSinhVien = d.sinhVien.idSinhVien JOIN MonHoc mh ON d.monHoc.idMonHoc = mh.idMonHoc WHERE mh.giangVien.idGiangVien = ?1")
    List<SinhVienDTO> getAllSinhVien(@Param("id_giangvien") Long idGiangVien);
    List<SinhVien> findByLop_IdLopAndLop_KhoaHoc_IdKhoaHoc(Long idLop, Long idKhoaHoc);
    List<SinhVien> findByLopIdLop(Long idLop);
    List<SinhVien> findByLopKhoaHocIdKhoaHoc(Long idKhoaHoc);
    List<SinhVien> findByLopIdLopAndLopKhoaHocIdKhoaHoc(Long idLop, Long idKhoaHoc);
}
