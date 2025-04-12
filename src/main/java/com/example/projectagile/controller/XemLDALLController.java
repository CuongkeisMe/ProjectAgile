package com.example.projectagile.controller;

import com.example.projectagile.model.KhoaHoc;
import com.example.projectagile.model.Lop;
import com.example.projectagile.model.SinhVien;
import com.example.projectagile.repository.KhoaHocRepository;
import com.example.projectagile.repository.LopRepository;
import com.example.projectagile.repository.SinhVienRepository;
import com.example.projectagile.service.KhoaHocService;
import com.example.projectagile.service.LopService;
import com.example.projectagile.service.SinhVienService;
import com.example.projectagile.service.xemDSSVOfAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller

public class XemLDALLController {
    private final xemDSSVOfAdService xemDSSVOfAdService;
    private final SinhVienRepository sinhVienRepository;
    private final LopRepository lopRepository;
    private final KhoaHocRepository khoaHocRepository;
    private final SinhVienService sinhVienService;
    private final LopService lopService;
    private final KhoaHocService khoaHocService;

    public XemLDALLController(xemDSSVOfAdService xemDSSVOfAdService, SinhVienRepository sinhVienRepository, LopRepository lopRepository, KhoaHocRepository khoaHocRepository, SinhVienService sinhVienService, LopService lopService, KhoaHocService khoaHocService) {
        this.xemDSSVOfAdService = xemDSSVOfAdService;
        this.sinhVienRepository = sinhVienRepository;
        this.lopRepository = lopRepository;
        this.khoaHocRepository = khoaHocRepository;
        this.sinhVienService = sinhVienService;
        this.lopService = lopService;
        this.khoaHocService = khoaHocService;
    }

        @GetMapping("/xemlich")
    public String xemlich(Model model, SinhVien sinhvien) {
        List<SinhVien> sv = xemDSSVOfAdService.getAllSV();
        model.addAttribute("sv", sv);
        List<Lop> lops = lopService.getAllLop();
        model.addAttribute("dsLop", lops);
        List<KhoaHoc> khoaHoc = khoaHocService.getAllKhoaHoc();
        model.addAttribute("dsKhoaHoc", khoaHoc);
        model.addAttribute("nameFile", "XemLichDayALl");
        return "layout";
    }


    @PostMapping("/sinhvien/search")
    public String searchSinhVien(
            @RequestParam(required = false) Long idLop,
            @RequestParam(required = false) Long idKhoaHoc,
            Model model) {

        List<SinhVien> svList;

        if (idLop != null && idKhoaHoc != null) {
            svList = sinhVienRepository.findByLopIdLopAndLopKhoaHocIdKhoaHoc(idLop, idKhoaHoc);
        } else if (idLop != null) {
            svList = sinhVienRepository.findByLopIdLop(idLop);
        } else if (idKhoaHoc != null) {
            svList = sinhVienRepository.findByLopKhoaHocIdKhoaHoc(idKhoaHoc);
        } else {
            svList = sinhVienRepository.findAll(); // không lọc gì cả
        }

        model.addAttribute("sv", svList);
        model.addAttribute("dsLop", lopRepository.findAll());
        model.addAttribute("dsKhoaHoc", khoaHocRepository.findAll());
        model.addAttribute("nameFile", "XemLichDayALl");
        return "layout";
    }

}
