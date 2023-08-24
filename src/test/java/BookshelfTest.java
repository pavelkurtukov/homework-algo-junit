import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BookshelfTest {

    @Test
    @DisplayName("Тест getLargerBooksCount")
    void getLargerBooksCount() {
        int[] testArr = {1, 2, 3, 4, 4, 5, 77, 88, 99};
        int elem = 4;
        int expected = 4;

        Assertions.assertEquals(expected, Bookshelf.getLargerBooksCount(testArr, elem));
    }
}