public class 机器人走到指定位置 {
    //现在有1~N N个位置 你一开始在M位置,走K步走到P位置的方法数
    public static int waysCache(int N, int M, int K, int P) {
        if (N < 2 || M < 1 || M > N || K < 1 || P < 1 || P > N) {
            return 0;
        }
        int[][] dp = new int[N + 1][K + 1];
        for (int row = 0; row <= N; row++) {
            for (int col = 0; col <= N; col++) {
                dp[row][col] = -1;
            }
        }
        return walksDp(N, M, K, P, dp);
    }

    //N是固定量表示总位置数
    //cur是可变了表示现在处于的位置
    //rest表示还剩rest步数
    //P表示最终要到达的位置
    public static int walksDp(int N, int cur, int rest, int P, int[][] dp) {
        if (dp[cur][rest] != -1) {
            return dp[cur][rest];
        }
        if (rest == 0) {
            dp[cur][rest] = cur == P ? 1 : 0;
            return dp[cur][rest];
        }
        //rest>0
        if (cur == 1) {
            dp[cur][rest] = walks(N, 2, rest - 1, P);
            return dp[cur][rest];
        }
        if (cur == N) {
            dp[cur][rest] = walks(N, N - 1, rest - 1, P);
            return dp[cur][rest];
        }
        dp[cur][rest] = walks(N, cur - 1, rest - 1, P) +
                walks(N, cur + 1, rest - 1, P);
        return dp[cur][rest];
    }

    //N表示总共有1~N个位置
    //cur表示现在所在的位置
    //rest表示还剩余的步数
    //P表示最终要到达的位置
    public static int walks(int N, int cur, int rest, int P) {
        //先写baseCase
        //步数为0步时，没有步数可走
        if (rest == 0) {
            return cur == P ? 1 : 0;
        }
        //rest>0
        if (cur == 1) {
            return walks(N, 2, rest - 1, P);
        }
        if (cur == N) {
            return walks(N, N - 1, rest - 1, P);
        }
        return walks(N, cur - 1, rest - 1, P) +
                walks(N, cur + 1, rest - 1, P);
    }

    //精细化动态规划
    public static int dpWay(int N, int M, int K, int P) {
        if (N < 2 || M < 1 || M > N || K < 1 || P < 1 || P > N) {
            return 0;
        }
        int[][] dp = new int[N + 1][K + 1];
        dp[P][0] = 1;
        for (int cur = 1; cur <= N; cur++) {
            for (int rest = 1; rest <= K; rest++) {
                if (cur == 1) {
                    dp[cur][rest] = dp[2][rest - 1];
                } else if (cur == N) {
                    dp[cur][rest] = dp[N - 1][rest - 1];
                } else {
                    dp[cur][rest] = dp[cur - 1][rest - 1] + dp[cur + 1][rest - 1];
                }
            }
        }
        return dp[M][K];
    }
}
