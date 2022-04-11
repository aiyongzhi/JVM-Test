public class Bob的生存概率 {
    //给定正数M,N,a,b,K
    //表示有一个M*N的棋盘，Bob一开始在棋盘的(a,b)位置。
    //Bob是一个醉汉，他每一步都等概率的向上下左右走一个格子
    //规定：Bob一旦走出了棋盘的范围就会被杀死
    //请问K步后，Bob还活着的概率是多少？
    public static double live(int M, int N, int a, int b, int K) {
        if (M <= 0 || N <= 0 || a < 0 || a == M || b < 0 || b == N || K <= 0) {
            return -1;
        }
        return process(a, b, M, N, K) / (Math.pow(4, K));
    }

    //在(a,b)位置，走rest步，还在棋盘中的方法数
    //暴力递归
    public static int process(int i, int j, int M, int N, int rest) {
        //base case
        //本质上属于剪枝优化
        if (i < 0 || i == M || j < 0 || j == N) {
            return 0;
        }
        //还在棋盘内
        if (rest == 0) {
            return 1;
        }
        return process(i - 1, j, M, N, rest - 1) +
                process(i + 1, j, M, N, rest - 1) +
                process(i, j - 1, M, N, rest - 1) +
                process(i, j + 1, M, N, rest - 1);
    }

    //改成动态规划
    public static double dpWays(int M, int N, int a, int b, int K) {
        if (M <= 0 || N <= 0 || a < 0 || a == M || b < 0 || b == N || K <= 0) {
            return -1;
        }
        int[][][] dp = new int[M][N][2];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j][0] = 1;
            }
        }
        //填普遍位置
        for (int rest = 1; rest <= K; rest++) {
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    dp[i][j][rest % 2] = getValue(i - 1, j, M, N, rest - 1, dp) +
                            getValue(i + 1, j, M, N, rest - 1, dp) +
                            getValue(i, j - 1, M, N, rest - 1, dp) +
                            getValue(i, j + 1, M, N, rest - 1, dp);
                }
            }
        }
        return dp[a][b][K % 2] / (Math.pow(4, K));
    }

    public static int getValue(int i, int j, int M, int N, int rest, int[][][] dp) {
        if (i < 0 || i == M || j < 0 || j == N) {
            return 0;
        }
        return dp[i][j][rest % 2];
    }

    public static void main(String[] args) {
        int M = 7;
        int N = 5;
        int a = 2;
        int b = 1;
        int K = 7;
        System.out.println(live(M, N, a, b, K));
        System.out.println(dpWays(M, N, a, b, K));
    }
}
