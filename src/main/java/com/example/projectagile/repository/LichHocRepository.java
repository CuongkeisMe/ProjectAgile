package com.example.projectagile.repository;

import com.example.projectagile.dto.LichDayDTO;
import com.example.projectagile.model.LichHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LichHocRepository extends JpaRepository<LichHoc, Long> {
    @Query("SELECT new com.example.projectagile.dto.LichDayDTO(mh.tenMonHoc, lh.ngayHoc, lh.gioBatDau, lh.gioKetThuc, l.maLop, l.tenLop) " +
            "FROM GiangVien gv " +
            "JOIN MonHoc mh ON gv.idGiangVien = mh.giangVien.idGiangVien " +
            "JOIN LichHoc lh ON mh.idMonHoc = lh.monHoc.idMonHoc " +
            "JOIN Diem d ON d.monHoc.idMonHoc = mh.idMonHoc " +
            "JOIN SinhVien sv ON sv.idSinhVien = d.sinhVien.idSinhVien " +
            "JOIN Lop l ON sv.lop.idLop = l.idLop " +
            "WHERE (:idGiangVien IS NULL OR mh.giangVien.idGiangVien = :idGiangVien)" +
            "GROUP BY mh.tenMonHoc, lh.ngayHoc, lh.gioBatDau, lh.gioKetThuc, l.maLop, l.tenLop " +
            "ORDER BY lh.ngayHoc, lh.gioBatDau")
    List<LichDayDTO> getLichDayByGiangVien(@Param("idGiangVien") Long idGiangVien);
}
