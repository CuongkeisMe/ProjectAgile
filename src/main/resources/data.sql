CREATE DATABASE StudentManagement;
GO
USE StudentManagement;
GO

-- Bảng User
CREATE TABLE [User] (
                        id_user INT IDENTITY(1,1) PRIMARY KEY,
    username NVARCHAR(50) UNIQUE NOT NULL,
    password NVARCHAR(255) NOT NULL,
    role NVARCHAR(20) CHECK (role IN ('admin', 'teacher', 'student')) NOT NULL
    );

-- Bảng KhoaHoc
CREATE TABLE KhoaHoc (
                         id_khoahoc INT IDENTITY(1,1) PRIMARY KEY,
                         ma_khoahoc NVARCHAR(50) UNIQUE NOT NULL,  -- Added ma_khoahoc column for code
                         ten_khoahoc NVARCHAR(100) NOT NULL,
                         thoi_gian_bat_dau DATE,
                         thoi_gian_ket_thuc DATE
);

-- Bảng Lop
CREATE TABLE Lop (
                     id_lop INT IDENTITY(1,1) PRIMARY KEY,
                     ma_lop NVARCHAR(50) UNIQUE NOT NULL,  -- Added ma_lop column for code
                     ten_lop NVARCHAR(50) NOT NULL,
                     id_khoahoc INT,
                     FOREIGN KEY (id_khoahoc) REFERENCES KhoaHoc(id_khoahoc)
);

-- Bảng SinhVien
CREATE TABLE SinhVien (
                          id_sinhvien INT IDENTITY(1,1) PRIMARY KEY,
                          ma_sinhvien NVARCHAR(50) UNIQUE NOT NULL,  -- Added ma_sinhvien column for code
                          ho_ten NVARCHAR(100) NOT NULL,
                          email NVARCHAR(100) UNIQUE NOT NULL,
                          so_dien_thoai NVARCHAR(15),
                          id_lop INT,
                          FOREIGN KEY (id_lop) REFERENCES Lop(id_lop)
);

-- Bảng GiangVien
CREATE TABLE GiangVien (
                           id_giangvien INT IDENTITY(1,1) PRIMARY KEY,
                           ma_giangvien NVARCHAR(50) UNIQUE NOT NULL,  -- Added ma_giangvien column for code
                           ho_ten NVARCHAR(100) NOT NULL,
                           email NVARCHAR(100) UNIQUE NOT NULL,
                           so_dien_thoai NVARCHAR(15)
);

-- Bảng MonHoc
CREATE TABLE MonHoc (
                        id_monhoc INT IDENTITY(1,1) PRIMARY KEY,
                        ma_monhoc NVARCHAR(50) UNIQUE NOT NULL,  -- Added ma_monhoc column for code
                        ten_monhoc NVARCHAR(100) NOT NULL,
                        id_giangvien INT,
                        FOREIGN KEY (id_giangvien) REFERENCES GiangVien(id_giangvien)
);

-- Bảng Diem
CREATE TABLE Diem (
                      id_diem INT IDENTITY(1,1) PRIMARY KEY,
                      id_sinhvien INT,
                      id_monhoc INT,
                      diem_so DECIMAL(4,2),
                      FOREIGN KEY (id_sinhvien) REFERENCES SinhVien(id_sinhvien),
                      FOREIGN KEY (id_monhoc) REFERENCES MonHoc(id_monhoc)
);

-- Bảng LichHoc
CREATE TABLE LichHoc (
                         id_lichhoc INT IDENTITY(1,1) PRIMARY KEY,
                         id_monhoc INT,
                         ngay_hoc DATE,
                         gio_bat_dau TIME,
                         gio_ket_thuc TIME,
                         FOREIGN KEY (id_monhoc) REFERENCES MonHoc(id_monhoc)
);

-- Bảng LichGiangDay
CREATE TABLE LichGiangDay (
                              id_lichgiangday INT IDENTITY(1,1) PRIMARY KEY,
                              id_giangvien INT,
                              id_monhoc INT,
                              ngay_day DATE,
                              FOREIGN KEY (id_giangvien) REFERENCES GiangVien(id_giangvien),
                              FOREIGN KEY (id_monhoc) REFERENCES MonHoc(id_monhoc)
);

-- Insert into [User] table
INSERT INTO [User] (username, password, role)
VALUES
    ('admin_user', 'admin_password', 'admin'),
    ('teacher_user', 'teacher_password', 'teacher'),
    ('student_user', 'student_password', 'student');

-- Insert into KhoaHoc table
INSERT INTO KhoaHoc (ma_khoahoc, ten_khoahoc, thoi_gian_bat_dau, thoi_gian_ket_thuc)
VALUES
    ('KH001', N'Lập Trình Java', '2025-01-01', '2025-06-01'),
    ('KH002', N'Cơ Sở Dữ Liệu', '2025-02-01', '2025-07-01'),
    ('KH003', N'Mạng Máy Tính', '2025-03-01', '2025-08-01'),
    ('KH004', N'Tiếng anh 1.1', '2025-01-21', '2025-03-30');

-- Insert into Lop table
INSERT INTO Lop (ma_lop, ten_lop, id_khoahoc)
VALUES
    ('L001', N'Lớp A - Lập Trình Java', 1),
    ('L002', N'Lớp B - Cơ Sở Dữ Liệu', 2),
    ('L003', N'Lớp C - Mạng Máy Tính', 3);

-- Insert into GiangVien table
INSERT INTO GiangVien (ma_giangvien, ho_ten, email, so_dien_thoai)
VALUES
    ('GV001', N'Lê Minh C', 'leminhc@example.com', '0912345678'),
    ('GV002', N'Phan Hoàng D', 'phanhoangd@example.com', '0976543210'),
    ('GV003', N'Tào Duy H', 'duyh1234@example.com', '0123456654');

-- Insert into MonHoc table
INSERT INTO MonHoc (ma_monhoc, ten_monhoc, id_giangvien)
VALUES
    ('MH001', N'Lập Trình Java', 1),
    ('MH002', N'Cơ Sở Dữ Liệu', 2),
    ('MH003', N'Mạng Máy Tính', 1),
    ('MH004', N'Tiếng anh 1.1', 3);

-- Insert into SinhVien table
-- 1st Lecturer (Lê Minh C) teaches 'Lập Trình Java' and 'Mạng Máy Tính'
INSERT INTO SinhVien (ma_sinhvien, ho_ten, email, so_dien_thoai, id_lop)
VALUES
    ('SV001', N'Nguyễn Văn A', 'nguyenvana@example.com', '0123456789', 1),
    ('SV002', N'Trần Thị B', 'tranthib@example.com', '0987654321', 1),
    ('SV003', N'Lê Minh C', 'leminhc2@example.com', '0123498765', 3),
    ('SV004', N'Nguyễn Thiên D', 'nguyenhiend@example.com', '0987321987', 1),
    ('SV005', N'Phan Thanh E', 'phanthanh@example.com', '0976123456', 1),
    ('SV006', N'Trần Lệ F', 'tranlef@example.com', '0907654321', 1),
    ('SV007', N'Lâm Hoàng G', 'lamhoangg@example.com', '0123456710', 3),
    ('SV008', N'Đặng Hoàng H', 'danghoangh@example.com', '0922334455', 1),
    ('SV009', N'Bùi Thái I', 'buihai@example.com', '0987123456', 1),
    ('SV010', N'Ngô Trúc K', 'ngotruc@example.com', '0912345678', 3),
    ('SV011', N'Võ Minh L', 'vominhl@example.com', '0987654321', 1),
    ('SV012', N'Trương Thiên M', 'truongm@example.com', '0931245789', 1),
    ('SV013', N'Đoàn Lê N', 'doanl@example.com', '0907654321', 1),
    ('SV014', N'Lê Quang O', 'lequang@example.com', '0987654321', 3),
    ('SV015', N'Trần Đại P', 'trandai@example.com', '0934567890', 3);

-- 2nd Lecturer (Phan Hoàng D) teaches 'Cơ Sở Dữ Liệu'
INSERT INTO SinhVien (ma_sinhvien, ho_ten, email, so_dien_thoai, id_lop)
VALUES
    ('SV016', N'Nguyễn Hoàng Q', 'nguyenhoangq@example.com', '0908765432', 2),
    ('SV017', N'Lê Hải R', 'lehaire@example.com', '0923456789', 2),
    ('SV018', N'Trần Thiên S', 'tranthienS@example.com', '0987654321', 3),
    ('SV019', N'Ngô Hoàng T', 'ngohangT@example.com', '0901234567', 2),
    ('SV020', N'Phan Minh U', 'phanminhu@example.com', '0934567890', 2),
    ('SV021', N'Lâm Thiên V', 'lamthienV@example.com', '0912345678', 3),
    ('SV022', N'Võ Đình W', 'vodinhW@example.com', '0935678901', 2),
    ('SV023', N'Bùi Minh X', 'buiminhX@example.com', '0987321987', 2),
    ('SV024', N'Trương Thiên Y', 'truongthienY@example.com', '0922334455', 2),
    ('SV025', N'Đoàn Quang Z', 'doanquangZ@example.com', '0908765432', 2);

-- Insert into Diem table (adding grades for students in each subject)
-- For 'Lập Trình Java' (Course ID 1)
INSERT INTO Diem (id_sinhvien, id_monhoc, diem_so)
VALUES
    (1, 1, 8.5), (2, 1, 9.0), (3, 1, 7.5), (4, 1, 8.0), (5, 1, 8.5),
    (6, 1, 9.2), (7, 1, 7.8), (8, 1, 8.0), (9, 1, 8.3), (10, 1, 9.1),
    (11, 1, 7.9), (12, 1, 8.4), (13, 1, 7.8), (14, 1, 8.6), (15, 1, 9.0);

-- For 'Cơ Sở Dữ Liệu' (Course ID 2)
INSERT INTO Diem (id_sinhvien, id_monhoc, diem_so)
VALUES
    (16, 2, 7.5), (17, 2, 8.0), (18, 2, 9.0), (19, 2, 8.5), (20, 2, 7.9),
    (21, 2, 8.6), (22, 2, 9.2), (23, 2, 8.0), (24, 2, 7.7), (25, 2, 9.0);

-- Insert into LichHoc table (schedules for courses)
-- For 'Lập Trình Java' (Course ID 1)
INSERT INTO LichHoc (id_monhoc, ngay_hoc, gio_bat_dau, gio_ket_thuc)
VALUES
    (1, '2025-01-15', '08:00', '10:00'),  -- Lập Trình Java schedule
    (1, '2025-01-20', '10:30', '12:30'),
    (1, '2025-01-25', '14:00', '16:00');

-- For 'Cơ Sở Dữ Liệu' (Course ID 2)
INSERT INTO LichHoc (id_monhoc, ngay_hoc, gio_bat_dau, gio_ket_thuc)
VALUES
    (2, '2025-02-10', '14:00', '16:00'),  -- Cơ Sở Dữ Liệu schedule
    (2, '2025-02-15', '09:00', '11:00'),
    (2, '2025-02-20', '13:30', '15:30');

-- Insert into LichGiangDay table (teaching schedule for each lecturer)
-- For 'Lê Minh C' teaching 'Lập Trình Java'
INSERT INTO LichGiangDay (id_giangvien, id_monhoc, ngay_day)
VALUES
    (1, 1, '2025-01-15'),
    (1, 1, '2025-01-20'),
    (1, 1, '2025-01-25');

-- For 'Phan Hoàng D' teaching 'Cơ Sở Dữ Liệu'
INSERT INTO LichGiangDay (id_giangvien, id_monhoc, ngay_day)
VALUES
    (2, 2, '2025-02-10'),
    (2, 2, '2025-02-15'),
    (2, 2, '2025-02-20');

