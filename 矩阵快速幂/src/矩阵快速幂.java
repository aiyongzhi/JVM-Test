import java.util.Arrays;

public class 矩阵快速幂 {
    //由斐波那契数列到矩阵快速幂
    //求用矩阵快速幂求斐波那契数列问题
    public static int getFibonacci(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        int[][] matrix = {{1, 1},
                {1, 0}};
        int[][] res = matrixPower(matrix, n - 2);
        return res[0][0] + res[1][0];
    }

    //矩阵的n次幂
    public static int[][] matrixPower(int[][] matrix, int p) {
        //用矩阵快速幂的方式求matrix的n次幂
        int[][] res = new int[matrix.length][matrix[0].length];
        //单位矩阵 代表数值为1
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
        int[][] tmp = matrix;//代表矩阵的1次方
        for (; p != 0; p >>= 1) {
            if ((p & 1) != 0) {//说明这一位要乘到res中去
                res = multiMatrix(res, tmp);
            }
            tmp = multiMatrix(tmp, tmp);
        }
        return res;
    }

    //写一个方法完成矩阵的乘法
    public static int[][] multiMatrix(int[][] m1, int[][] m2) {
        int[][] res = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m1[0].length; k++) {
                    res[i][j] += (m1[i][k] * m2[k][j]);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(getFibonacci(5));
    }
}
