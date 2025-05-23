package com.example.projectagile.repository;

import com.example.projectagile.model.GiangVien;
import com.example.projectagile.model.SinhVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GiangVienRepository extends JpaRepository<GiangVien, Long> {
    boolean existsBysoDienThoai(String phone);
    @Query("SELECT gv FROM GiangVien gv WHERE gv.user.username = :username")
    GiangVien findByUsername(@Param("username") String username);
}
