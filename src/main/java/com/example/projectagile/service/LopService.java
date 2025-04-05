package com.example.projectagile.service;

import com.example.projectagile.model.Lop;
import com.example.projectagile.repository.LopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LopService implements ILopService {

    private final LopRepository lopRepository;

    @Override
    public List<Lop> getAllLop() {
        return lopRepository.findAll();
    }

}
