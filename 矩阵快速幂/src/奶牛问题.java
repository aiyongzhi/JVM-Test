public class 奶牛问题 {
    //题意：假设农场第一年有一头母牛，有如下规定：
    //1:每头成熟的母牛每年可以生一个牛宝宝
    //2:牛宝宝过三年才能成熟成可以生产的母牛
    //3:所有牛都不会死
    //求n年后，农场牛的个数
    //递推公式T(N)=T(N-1)+T(N-3)
    public static int cowCounts(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        int[][] matrix = {
                {1, 1, 0},
                {0, 0, 1},
                {1, 0, 0}};
        int[][] res = matrixPower(matrix, n - 3);
        return 3 * res[0][0] + 2 * res[1][0] + res[2][0];
    }

    public static int[][] matrixPower(int[][] matrix, int p) {
        //用矩阵快速幂的方式求matrix的n次幂
        int[][] res = new int[matrix.length][matrix.length];
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

    //对数器
    //暴力递归
    public static int process(int n) {
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        return process(n - 1) + process(n - 3);
    }

    //改成动态规划
    public static int dpWays(int n) {
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        for (int i = 4; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 3];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int testTime = 100000;
        int maxValue = 100;
        for (int i = 0; i < testTime; i++) {
            int year = (int) (Math.random() * maxValue) + 1;
            int ans1 = dpWays(year);
            int ans2 = cowCounts(year);
            if (ans1 != ans2) {
                System.out.println("fuck!");
                System.out.println(year);
                break;
            }
        }
        System.out.println("Ops!");
    }
}
