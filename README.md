# Software-Testing
Bùi Tuấn Anh BIT230480

Kết quả bài tập thực hành tuần 1 trên: https://cantunsee.space/
<img width="1802" height="893" alt="Capture" src="https://github.com/user-attachments/assets/54337aea-2d2d-41d2-bb31-987b71394aeb" />


## Tuần 2 – Kiểm thử đơn vị với JUnit

**Chủ đề**: Phân tích dữ liệu điểm số học sinh (lớp StudentAnalyzer)

**Mục tiêu**:
- Implement hai phương thức: `countExcellentStudents()` và `calculateValidAverage()`
- Viết unit test đầy đủ bằng JUnit 5
- Sử dụng GitHub Issues để quản lý công việc
- Commit liên kết với issues

**Cấu trúc dự án**:
unit-test/
├── src/
│   └── StudentAnalyzer.java
└── test/
└── StudentAnalyzerTest.java


**Các issues đã tạo và thực hiện**:
- [#1] Viết hàm countExcellentStudents() → Đã hoàn thành
- [#2] Viết hàm calculateValidAverage() → Đã hoàn thành
- [#3] Viết test cho 2 hàm trên → Đã hoàn thành (JUnit 5, bao quát normal, boundary, invalid, null, floating-point precision)
- [#4] Viết tài liệu README.md → Đang thực hiện

**Các test case chính đã viết**:
- **countExcellentStudents**:
  - Normal: Có điểm hợp lệ + invalid → đếm đúng số giỏi
  - Boundary: Danh sách rỗng, null, exactly 8.0, 10.0
  - Invalid: Điểm <0, >10, phần tử null
- **calculateValidAverage**:
  - Normal: Có invalid → tính trung bình chỉ các điểm hợp lệ
  - Boundary: Rỗng, null, tất cả invalid → trả về 0.0
  - Precision: Sử dụng delta 0.001 hoặc 0.01 khi assert double
  - Single valid point, min/max boundary

**Commit liên kết**:
- feat: implement countExcellentStudents and calculateValidAverage #1
- test: add comprehensive unit tests for both methods #3
- docs: update README with week 2 report #4
