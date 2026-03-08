import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentAnalyzerTest {

    private StudentAnalyzer analyzer;

    @BeforeEach
    void setUp() {
        analyzer = new StudentAnalyzer();
    }

    // === Test cho phương thức countExcellentStudents ===

    @Test
    void testCountExcellentStudents_NormalWithInvalidData() {
        List<Double> scores = Arrays.asList(9.0, 8.5, 7.9, 11.0, -1.0, 8.0, null);
        assertEquals(3, analyzer.countExcellentStudents(scores),
                "Nên đếm đúng 3 học sinh giỏi (9.0, 8.5, 8.0), bỏ qua invalid và null");
    }

    @Test
    void testCountExcellentStudents_AllValidButNoExcellent() {
        assertEquals(0, analyzer.countExcellentStudents(Arrays.asList(7.9, 6.5, 5.0)),
                "Không có học sinh nào >= 8.0");
    }

    @Test
    void testCountExcellentStudents_EmptyList() {
        assertEquals(0, analyzer.countExcellentStudents(Collections.emptyList()),
                "Danh sách rỗng phải trả về 0");
    }

    @Test
    void testCountExcellentStudents_NullList() {
        assertEquals(0, analyzer.countExcellentStudents(null),
                "Danh sách null phải trả về 0");
    }

    @Test
    void testCountExcellentStudents_BoundaryExactly8And10() {
        List<Double> scores = Arrays.asList(8.0, 10.0, 7.999);
        assertEquals(2, analyzer.countExcellentStudents(scores),
                "Chỉ đếm >= 8.0, 7.999 không được tính");
    }

    @Test
    void testCountExcellentStudents_OnlyInvalidAndNull() {
        assertEquals(0, analyzer.countExcellentStudents(Arrays.asList(-0.1, 10.1, null, null)),
                "Tất cả invalid → trả về 0");
    }

    // === Test cho phương thức calculateValidAverage ===

    @Test
    void testCalculateValidAverage_NormalWithInvalidData() {
        List<Double> scores = Arrays.asList(9.0, 8.5, 7.0, 11.0, -1.0, null);
        // Valid: 9.0 + 8.5 + 7.0 = 24.5 → trung bình 24.5 / 3 ≈ 8.1667
        assertEquals(8.17, analyzer.calculateValidAverage(scores), 0.01,
                "Trung bình các điểm hợp lệ phải ≈ 8.17 (delta 0.01)");
    }

    @Test
    void testCalculateValidAverage_AllValid() {
        assertEquals(8.5, analyzer.calculateValidAverage(Arrays.asList(8.0, 9.0)), 0.001,
                "Trung bình chính xác 8.5");
    }

    @Test
    void testCalculateValidAverage_EmptyList() {
        assertEquals(0.0, analyzer.calculateValidAverage(Collections.emptyList()), 0.001,
                "Danh sách rỗng trả về 0.0");
    }

    @Test
    void testCalculateValidAverage_NullList() {
        assertEquals(0.0, analyzer.calculateValidAverage(null), 0.001,
                "Danh sách null trả về 0.0");
    }

    @Test
    void testCalculateValidAverage_AllInvalid() {
        assertEquals(0.0, analyzer.calculateValidAverage(Arrays.asList(-1.0, 11.0, null)), 0.001,
                "Không có điểm hợp lệ → trả về 0.0");
    }

    @Test
    void testCalculateValidAverage_BoundaryMinMax() {
        assertEquals(5.0, analyzer.calculateValidAverage(Arrays.asList(0.0, 10.0)), 0.001,
                "Trung bình giữa min và max hợp lệ");
    }

    @Test
    void testCalculateValidAverage_SingleValidPoint() {
        assertEquals(9.5, analyzer.calculateValidAverage(Arrays.asList(9.5, -5.0, 12.0)), 0.001,
                "Chỉ một điểm hợp lệ → trung bình bằng chính điểm đó");
    }
}
