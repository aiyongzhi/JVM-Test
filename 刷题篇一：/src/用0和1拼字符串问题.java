public class 用0和1拼字符串问题 {
    //给你一个整数N，要求你只用0和1拼成长度为N的字符串
    //我们把所有0字符左边都紧挨着1字符的称为达标字符串
    //请你返回达标字符串的数目

    //写一个暴力递归
    public static int process1(int i, int N) {
        if (i >= N) {
            return 1;
        }
        return process1(i + 1, N) + process1(i + 2, N);
    }

    //递归含义：现在有长度为N的字符串可以自由拼接返回方法数
    //显然会有重复解：改成记忆化搜索
    public static int process2(int N) {
        //base case
        if (N <= 0) {
            return 1;
        }
        return process2(N - 1) + process2(N - 2);
    }

    //记忆化搜索
    //需要加缓存
    public static int getAllWays(int N) {
        if (N <= 0) {
            return 0;
        }
        int[] dp = new int[N + 1];
        //先初始化
        for (int i = 0; i < dp.length; i++) {
            dp[i] = -1;
        }
        return process3(N - 1, dp);
    }

    public static int process3(int N, int[] dp) {
        if (dp[N + 1] != -1) {
            return dp[N + 1];
        }
        //base case
        if (N <= 0) {
            dp[N + 1] = 1;
            return 1;
        }
        dp[N + 1] = process2(N - 1) + process2(N - 2);
        return dp[N + 1];
    }

    //可以改成动态规划
    //时间复杂度为O(N) 空间复杂度为O(N)
    public static int dpWays(int N) {
        if (N <= 0) {
            return 0;
        }
        int[] dp = new int[N];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[N - 1];
    }

    //法四：矩阵快速幂
    public static int getWays(int N) {
        if (N <= 0) {
            return 0;
        }
        int[][] matrix = {
                {1, 1},
                {1, 0}};
        int[][] res = matrixPower(matrix, N - 2);
        return 2 * res[0][0] + res[1][0];
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

    public static void main(String[] args) {
        int N = 8;
        System.out.println(process1(0, N - 1));
        System.out.println(process2(N - 1));
        System.out.println(getAllWays(N));
        System.out.println(dpWays(N));
        System.out.println(getWays(N));
    }
}
