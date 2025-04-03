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
                         ten_khoahoc NVARCHAR(100) NOT NULL,
                         thoi_gian_bat_dau DATE,
                         thoi_gian_ket_thuc DATE
);

-- Bảng Lop
CREATE TABLE Lop (
                     id_lop INT IDENTITY(1,1) PRIMARY KEY,
                     ten_lop NVARCHAR(50) NOT NULL,
                     id_khoahoc INT,
                     FOREIGN KEY (id_khoahoc) REFERENCES KhoaHoc(id_khoahoc)
);

-- Bảng SinhVien
CREATE TABLE SinhVien (
                          id_sinhvien INT IDENTITY(1,1) PRIMARY KEY,
                          ho_ten NVARCHAR(100) NOT NULL,
                          email NVARCHAR(100) UNIQUE NOT NULL,
                          so_dien_thoai NVARCHAR(15),
                          id_lop INT,
                          FOREIGN KEY (id_lop) REFERENCES Lop(id_lop)
);

-- Bảng GiangVien
CREATE TABLE GiangVien (
                           id_giangvien INT IDENTITY(1,1) PRIMARY KEY,
                           ho_ten NVARCHAR(100) NOT NULL,
                           email NVARCHAR(100) UNIQUE NOT NULL,
                           so_dien_thoai NVARCHAR(15)
);

-- Bảng MonHoc
CREATE TABLE MonHoc (
                        id_monhoc INT IDENTITY(1,1) PRIMARY KEY,
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


-- Bảng KhoaHoc (5 dòng)
INSERT INTO KhoaHoc (ten_khoahoc, thoi_gian_bat_dau, thoi_gian_ket_thuc)
VALUES
    ('Khoa CNTT', '2024-01-01', '2027-01-01'),
    ('Khoa Kinh Tế', '2024-02-01', '2027-02-01'),
    ('Khoa Cơ Khí', '2024-03-01', '2027-03-01'),
    ('Khoa Điện Tử', '2024-04-01', '2027-04-01'),
    ('Khoa Y Dược', '2024-05-01', '2027-05-01');

-- Bảng Lop (10 dòng)
INSERT INTO Lop (ten_lop, id_khoahoc)
VALUES
    ('Lop CNTT 1', 1),
    ('Lop CNTT 2', 1),
    ('Lop CNTT 3', 1),
    ('Lop Kinh Tế 1', 2),
    ('Lop Kinh Tế 2', 2),
    ('Lop Cơ Khí 1', 3),
    ('Lop Cơ Khí 2', 3),
    ('Lop Điện Tử 1', 4),
    ('Lop Điện Tử 2', 4),
    ('Lop Y Dược 1', 5);

-- Bảng SinhVien (20 dòng)
INSERT INTO SinhVien (ho_ten, email, so_dien_thoai, id_lop)
VALUES
    ('Nguyen Van A', 'a@gmail.com', '0123456789', 1),
    ('Tran Thi B', 'b@gmail.com', '0987654321', 1),
    ('Le Van C', 'c@gmail.com', '0345678912', 2),
    ('Hoang Thi D', 'd@gmail.com', '0356789123', 2),
    ('Pham Van E', 'e@gmail.com', '0367891234', 3),
    ('Bui Thi F', 'f@gmail.com', '0378912345', 3),
    ('Do Van G', 'g@gmail.com', '0389123456', 4),
    ('Ngo Thi H', 'h@gmail.com', '0391234567', 4),
    ('Vu Van I', 'i@gmail.com', '0312345678', 5),
    ('Dang Thi J', 'j@gmail.com', '0323456789', 5);

-- Bảng GiangVien (10 dòng)
INSERT INTO GiangVien (ho_ten, email, so_dien_thoai)
VALUES
    ('Thầy Nguyễn Văn M', 'm@gmail.com', '0901234567'),
    ('Cô Trần Thị N', 'n@gmail.com', '0912345678'),
    ('Thầy Lê Văn P', 'p@gmail.com', '0923456789'),
    ('Cô Hoàng Thị Q', 'q@gmail.com', '0934567890'),
    ('Thầy Phạm Văn R', 'r@gmail.com', '0945678901');

-- Bảng MonHoc (10 dòng)
INSERT INTO MonHoc (ten_monhoc, id_giangvien)
VALUES
    ('Lập trình Java', 1),
    ('Kế toán tài chính', 2),
    ('Cơ học kỹ thuật', 3),
    ('Quản trị kinh doanh', 4),
    ('Hệ thống nhúng', 5);

-- Bảng LichGiangDay (10 dòng)
INSERT INTO LichGiangDay (id_giangvien, id_monhoc, ngay_day)
VALUES
    (1, 1, '2024-09-01'),
    (2, 2, '2024-09-02'),
    (3, 3, '2024-09-03'),
    (4, 4, '2024-09-04'),
    (5, 5, '2024-09-05');

-- Bảng Diem (20 dòng)
INSERT INTO Diem (id_sinhvien, id_monhoc, diem_so)
VALUES
    (1, 1, 8.5),
    (2, 1, 7.5),
    (3, 2, 9.0),
    (4, 2, 6.5),
    (5, 3, 8.0);

-- Bảng User (5 dòng)
INSERT INTO Users (username, password, role)
VALUES
    ('admin', 'admin123', 'admin'),
    ('teacher1', 'teacher123', 'teacher'),
    ('student1', 'student123', 'student'),
    ('teacher2', 'teacher456', 'teacher'),
    ('student2', 'student456', 'student');
