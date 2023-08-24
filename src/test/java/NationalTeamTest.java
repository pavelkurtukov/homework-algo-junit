import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NationalTeamTest {

    @Test
    @DisplayName("Тест слияния массивов с ограничением")
    void merge() {
        // Массивы array1 и array2 должны быть отсортированы по убыванию
        int[] array1 = {17, 9, 8, 7, 1, 0};
        int[] array2 = {22, 15, 12, 6, 5, 1};
        int length = 7;
        int[] expectedResultArray = {22, 17, 15, 12, 9, 8, 7};

        int[] resultArray = NationalTeam.merge(array1, array2, length);

        Assertions.assertArrayEquals(resultArray, expectedResultArray);
    }
}