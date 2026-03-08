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


## Tuần 3 – Kiểm thử End-to-End với Cypress

**Mục tiêu**: Thực hành kiểm thử tự động E2E trên trang mẫu https://www.saucedemo.com (Swag Labs).

**Cấu trúc dự án**:
- `week3-cypress/`
  - `cypress/e2e/login.cy.js` → Kiểm tra đăng nhập thành công & thất bại
  - `cypress/e2e/cart.cy.js` → Thêm sản phẩm, sort giá, xóa sản phẩm, quy trình checkout

**Các kịch bản đã thực hiện**:
1. Đăng nhập thành công (standard_user / secret_sauce) → chuyển đến inventory.html
2. Đăng nhập thất bại → hiển thị thông báo lỗi đúng
3. Thêm sản phẩm vào giỏ hàng → badge hiển thị số 1
4. Sort sản phẩm theo giá thấp đến cao → sản phẩm đầu tiên giá $7.99
5. Xóa sản phẩm khỏi giỏ hàng → badge biến mất, giỏ rỗng
6. Quy trình checkout cơ bản → điền info → chuyển đến checkout-step-two.html

**Cách chạy test**:
1. `cd week3-cypress`
2. `npm install` (nếu clone mới)
3. `npx cypress open` → chọn E2E → chạy file test trong GUI Chrome

**Kết quả**: Tất cả test case passed (xanh 100%).  
<img width="1097" height="923" alt="image" src="https://github.com/user-attachments/assets/d8dc26b4-7961-4ae2-9932-70c971b25ba2" />
<img width="1100" height="840" alt="image" src="https://github.com/user-attachments/assets/b43e922b-e1e1-4067-83f5-ba425833fbe3" />
<img width="1131" height="652" alt="image" src="https://github.com/user-attachments/assets/b6af7005-be2c-489a-bab3-cc2254cf26e0" />


## Tuần 4 – Kiểm thử hiệu năng với JMeter

**Mục tiêu**:  
- Hiểu và thực hành kiểm thử hiệu năng (performance/load testing) bằng JMeter.  
- Thiết kế 3 kịch bản kiểm thử với tham số khác nhau (số lượng người dùng, ramp-up, hành vi).  
- Phân tích kết quả (Response Time, Throughput, Error Rate).

**Website kiểm thử**:  
https://blazedemo.com  


**Thiết lập chung**:
- HTTP Request Defaults: Protocol https, Server Name blazedemo.com  
- HTTP Cookie Manager (nếu cần xử lý session, nhưng site này không yêu cầu login).  
- Listeners: Summary Report riêng cho từng Thread Group (để kết quả tách biệt, dễ phân tích).  
- Chạy từng Thread Group riêng để tránh lẫn kết quả.

**3 kịch bản kiểm thử**:

1. **Kịch bản 1: Baseline (cơ bản – kiểm tra tải nhẹ)**  
   - Thread Group: Baseline - 10 users  
   - Số lượng người dùng: 10  
   - Ramp-up period: 10 giây  
   - Loop Count: 5 lần  
   - Hành vi: Chỉ GET trang chủ (`/`) – mô phỏng người dùng truy cập ban đầu.  

   **Kết quả chính** (từ Summary Report):  
   - Samples: [số request thực tế]  
   - Average Response Time: [xx] ms  
   - Throughput: [yy] requests/second  
   - Error %: 0%  
   - Min/Max RT: [min] / [max] ms  

   <img width="1920" height="999" alt="Capture" src="https://github.com/user-attachments/assets/ba12d7c2-f70b-4e05-8a9b-d9129a5d399a" />


2. **Kịch bản 2: Load nặng (kiểm tra tải đột biến)**  
   - Thread Group: Load - 50 users  
   - Số lượng người dùng: 50  
   - Ramp-up period: 30 giây  
   - Loop Count: 3 lần  
   - Hành vi: GET trang chủ (`/`) + POST tìm chuyến bay (`/reserve.php`) với tham số fromPort=Paris & toPort=Buenos Aires.  

   **Kết quả chính**:  
   - Samples: [số request thực tế]  
   - Average Response Time: [zz] ms (tăng so với baseline do tải cao hơn)  
   - Throughput: [ww] requests/second  
   - Error %: 0%  
   - 90th Percentile: [uu] ms  

   <img width="1920" height="979" alt="Capture1" src="https://github.com/user-attachments/assets/84376c27-1f2f-4356-b525-817dff9367d7" />


3. **Kịch bản 3: Custom (tùy chỉnh – mô phỏng hành vi hỗn hợp)**  
   - Thread Group: Custom - 20 users  
   - Số lượng người dùng: 20  
   - Ramp-up period: 20 giây  
   - Scheduler Duration: 60 giây  
   - Hành vi: GET trang chủ + POST tìm chuyến bay + GET trang kết quả (nếu có param cụ thể, ví dụ /purchase.php).  

   **Kết quả chính**:  
   - Samples: [số request thực tế]  
   - Average Response Time: [vv] ms  
   - Throughput: [tt] requests/second  
   - Error %: 0%  
   - 90th Percentile: [ss] ms  

   <img width="1920" height="1003" alt="Capture2" src="https://github.com/user-attachments/assets/848f0be1-616d-4621-a10c-a09c6ae8f85d" />
