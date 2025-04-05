package com.example.projectagile.repository;

import com.example.projectagile.model.Lop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LopRepository extends JpaRepository<Lop, Long> {
}
