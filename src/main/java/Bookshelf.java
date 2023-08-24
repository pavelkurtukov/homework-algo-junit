/*
У вас есть книжная полка, у каждой книги есть размер - количество страниц.
Книжная полка представлена массивом, в котором хранятся размеры книг в порядке возрастания.
Вам надо написать функцию, которая принимала бы этот массив размеров текущих книг,
размер новой книги и вычисляла бы количество больших по размеру книг уже на полке.
Требуемая алгоритмическая сложность: время O(log2n), дополнительная память O(1).
 */

public class Bookshelf {
    public static void main(String[] args) {
        // Исходный отсортированный массив с кол-вом страниц в книгах
        int[] books = new int[] { 14, 16, 19, 32, 32, 32, 56, 69, 72 };

        System.out.println(getLargerBooksCount(books, 33));
        System.out.println(getLargerBooksCount(books, 60));
    }

    // Поиск в массиве arr кол-во элементов, больших, чем elem
    private static int getLargerBooksCount(int[] arr, int elem) {
        int left = 0;
        int right = arr.length - 1;
        int largerCount = 0;

        while (left < right) {
            int middle = left + (right - left) / 2;
            if (arr[middle] > elem) {
                largerCount += right - middle + 1;
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        if (arr[right] > elem) largerCount++;

        return largerCount;
    }
}
