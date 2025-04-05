package com.example.projectagile.repository;

import com.example.projectagile.model.Diem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiemRepository extends JpaRepository<Diem, Long> {
}
