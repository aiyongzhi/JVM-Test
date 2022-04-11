public class 达标字符串的数目 {
    /*
     * 给你一个正整数N表示字符串的长度，想象只由'0'和'1'组成的所有长度为N的字符串中，
     * 只有所有'0'字符左侧都紧挨着'1'字符的字符串为达标字符串,请返回达标字符串的数目
     * */
    public static int getAllWays1(int N) {
        if (N < 1) {
            return 0;
        }
        if (N == 1) {
            return 1;
        }
        return process(1, N - 1);
    }

    //暴力递归
    //从左向后逐个填字符的模型
    //还剩rest个字符没填,当前位置可以任意选择填'0'和'1'，但会对后序选择产生影响
    //请返回组成达标字符串的数目
    public static int process(int i, int N) {
        if (i > N) {
            return 1;
        }
        return process(i + 1, N) + process(i + 2, N);
    }

    //改成动态规划
    public static int dpWays(int N) {
        if (N < 1) {
            return 0;
        }
        if (N == 1) {
            return 1;
        }
        int[] dp = new int[N + 2];
        dp[N] = 1;
        dp[N + 1] = 1;
        for (int i = N - 1; i >= 1; i--) {
            dp[i] = dp[i + 1] + dp[i + 2];
        }
        return dp[1];
    }


    //矩阵快速幂再次优化
    public static int getAllWays2(int N) {
        if (N < 1) {
            return 0;
        }
        if (N == 1 || N == 2) {
            return N;
        }
        int[][] matrix = {{1, 1},
                {1, 0}};
        int[][] res = matrixPower(matrix, N - 2);
        return res[0][0] * 2 + res[1][0];
    }

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
        int testTime = 100000;
        int maxValue = 100;
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxValue) + 1;
            int ans1 = dpWays(len);
            int ans2 = getAllWays2(len);
            if (ans1 != ans2) {
                System.out.println("fuck!");
                System.out.println(len);
                break;
            }
        }
        System.out.println("Ops!");
    }
}
