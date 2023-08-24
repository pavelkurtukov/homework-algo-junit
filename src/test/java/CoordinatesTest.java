import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class CoordinatesTest {

    @ParameterizedTest
    @MethodSource("testEqualsParams")
    @DisplayName("Тест сравнения эквивалентности координат")
    public void testEqualsWithParams(int x1, int y1, int x2, int y2, boolean expectedResult) {
        Coordinates c1 = new Coordinates(x1, y1);
        Coordinates c2 = new Coordinates(x2, y2);

        Assertions.assertEquals(expectedResult, c1.equals(c2) && c2.equals(c1));
    }

    public static Stream<Arguments> testEqualsParams() {
        return Stream.of(
                Arguments.of(1, 1, 1, 1, true),
                Arguments.of(1, 2, 2, 1, false)
        );
    }
}