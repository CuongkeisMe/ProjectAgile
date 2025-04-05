package com.example.projectagile.repository;

import com.example.projectagile.model.LichGiangDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LichGiangDayRepository extends JpaRepository<LichGiangDay, Long> {
}
