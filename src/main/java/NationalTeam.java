import java.util.Arrays;

public class NationalTeam {
    public static void main(String[] args) {
        int[][] regionalTeams = new int[][] {
                { 45, 31, 24, 22, 20, 17, 14, 13, 12, 10 },
                { 31, 18, 15, 12, 10, 8, 6, 4, 2, 1 },
                { 51, 30, 10, 9, 8, 7, 6, 5, 2, 1 }
        };
        int[] topTeam = new int[10];

        for (int i = 0; i < regionalTeams.length; i++) {
            topTeam = merge(topTeam, regionalTeams[i], topTeam.length);
            System.out.println("Региональная команда №" + (i + 1) + ": " + Arrays.toString(regionalTeams[i]));
        }
        System.out.println("Национальная сборная команда: " + Arrays.toString(topTeam));
    }

    // Сортировка слиянием, ограниченная длиной результирующего массива length
    public static int[] merge(int[] array1, int[] array2, int length) {
        int[] result = new int[length];
        for (int i = 0, i1 = 0, i2 = 0; i < length; i++) {
            if (i1 == array1.length) {
                result[i] = array2[i2++];
            } else if (i2 == array2.length) {
                result[i] = array1[i1++];
            } else {
                if (array1[i1] >= array2[i2]) {
                    result[i] = array1[i1++];
                } else {
                    result[i] = array2[i2++];
                }
            }
        }
        return result;
    }
}
