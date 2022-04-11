import java.util.Scanner;

public class Test {
    public static void process(int[][] matrix, int i, int j, int date, int n, int m) {
        if (i >= 0 && i < n && j >= 0 && j < m && matrix[i][j] == date) {
            matrix[i][j] = 0;
            process(matrix, i - 1, j, date, n, m);
            process(matrix, i + 1, j, date, n, m);
            process(matrix, i, j - 1, date, n, m);
            process(matrix, i, j + 1, date, n, m);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        //
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] != 0) {
                    res++;
                    process(matrix, i, j, matrix[i][j], n, m);
                }
            }
        }
        System.out.println(res);
    }
}
