package com.example.projectagile.repository;

import com.example.projectagile.model.MonHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonHocRepository extends JpaRepository<MonHoc, Long> {
    @Query("SELECT m FROM MonHoc m WHERE m.giangVien.idGiangVien = :giangVienId")
    List<MonHoc> findAllByGiangVienId(@Param("giangVienId") Long giangVienId);
}
