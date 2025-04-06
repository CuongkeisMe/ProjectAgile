package com.example.projectagile.service;

import com.example.projectagile.model.MonHoc;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IMonHocService {
    List<MonHoc> findAllByGiangVienId(Long giangVienId);
}
