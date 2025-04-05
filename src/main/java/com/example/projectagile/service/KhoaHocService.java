package com.example.projectagile.service;

import com.example.projectagile.model.KhoaHoc;
import com.example.projectagile.repository.KhoaHocRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KhoaHocService implements IKhoaHocService {
    private final KhoaHocRepository khoaHocRepository;

    @Override
    public List<KhoaHoc> getAllKhoaHoc() {
        return khoaHocRepository.findAll();
    }
}
