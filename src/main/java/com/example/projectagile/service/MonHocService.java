package com.example.projectagile.service;

import com.example.projectagile.model.MonHoc;
import com.example.projectagile.repository.MonHocRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MonHocService implements IMonHocService {

    private final MonHocRepository monHocRepository;


    @Override
    public List<MonHoc> findAllByGiangVienId(Long giangVienId) {
        return monHocRepository.findAllByGiangVienId(giangVienId);
    }
    public List<MonHoc> getAllMH() {
        return monHocRepository.findAll();
    }

    public void save(MonHoc monHoc) {
        monHocRepository.save(monHoc);
    }
    public Page<MonHoc> getpage(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return monHocRepository.findAll(pageRequest);
    }
}
