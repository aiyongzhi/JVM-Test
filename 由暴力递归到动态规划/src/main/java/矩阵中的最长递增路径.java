public class 矩阵中的最长递增路径 {
    //暴力递归
    public static int maxPath(int[][] matrix) {
        //刷掉无效数据
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int max = 0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                max = Math.max(process(matrix, i, j, dp), max);
            }
        }
        return max;
    }

    //写一个递归函数 任意i,j位置为起点能走出的最大递增路径、
    public static int process(int[][] matrix, int i, int j, int[][] dp) {
        //在递归前控制条件防止越界
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        int res1 = 0;
        int res2 = 0;
        int res3 = 0;
        int res4 = 0;
        if (i - 1 >= 0 && matrix[i - 1][j] > matrix[i][j]) {
            res1 = process(matrix, i - 1, j, dp);
        }
        if (i + 1 < matrix.length && matrix[i + 1][j] > matrix[i][j]) {
            res2 = process(matrix, i + 1, j, dp);
        }
        if (j - 1 >= 0 && matrix[i][j - 1] > matrix[i][j]) {
            res3 = process(matrix, i, j - 1, dp);
        }
        if (j + 1 < matrix[0].length && matrix[i][j + 1] > matrix[i][j]) {
            res4 = process(matrix, i, j + 1, dp);
        }
        dp[i][j] = 1 + Math.max(Math.max(res1, res2), Math.max(res3, res4));
        return dp[i][j];
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{9, 9, 4}, {6, 6, 8}, {2, 1, 1}};
        System.out.println(maxPath(matrix));
    }
}
