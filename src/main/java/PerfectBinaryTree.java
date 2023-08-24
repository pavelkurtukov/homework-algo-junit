public class PerfectBinaryTree {
    public static void main(String[] args) {
        int[] data = new int[] { 10, 13, 16, 19, 22, 25, 28, 31, 34, 37, 40, 43, 46, 49, 52 };
        buildTree(data);
    }

    public static void mark(int[] data, int left, int right, int level, int[] levels) {
        if (left == right) {
            levels[left] = level;
            return;
        }
        int middle = (right + left) / 2;
        levels[middle] = level;
        mark(data, left, middle - 1, level + 1, levels);
        mark(data, middle + 1, right, level + 1, levels);
    }

    public static void buildTree(int[] data) {
        int[] levels = new int[data.length];
        // Оределяем уровни элементов исходного массива в пирамиде, начиная с 0 уровня
        mark(data, 0, data.length - 1, 0, levels);
        // Отрисовываем дерево
        for (int level = 0; level <= levels[0]; level++) {
            for (int i = 0; i < data.length; i++) {
                if (levels[i] == level) {
                    System.out.print(data[i]);
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }
}
