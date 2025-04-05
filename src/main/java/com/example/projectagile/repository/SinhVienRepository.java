package com.example.projectagile.repository;

import com.example.projectagile.model.SinhVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SinhVienRepository extends JpaRepository<SinhVien, Long> {
//    @Query("SELECT sv FROM SinhVien sv JOIN sv.lop l JOIN MonHoc mh ON mh.idMonHoc = l.idLop " +
//            "WHERE mh.giangVien.idGiangVien = :giangVienId")
//    List<SinhVien> findByGiangVienId(@Param("giangVienId") Long giangVienId);
}
