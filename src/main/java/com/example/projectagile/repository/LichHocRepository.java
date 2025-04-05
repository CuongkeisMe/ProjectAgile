package com.example.projectagile.repository;

import com.example.projectagile.model.LichHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LichHocRepository extends JpaRepository<LichHoc, Long> {
}
