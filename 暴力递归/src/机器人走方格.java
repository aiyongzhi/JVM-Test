public class 机器人走方格 {
    //假设有排成一行的N个位置，记为1-N(N一定大于等于2)
    //机器人一开始在M位置(1<=M<=N),请问机器人必须走k步，最终走到P(1<=P<=N)位置的方法数
    //现规定机器人在1位置时，只能向右走到2位置;
    //机器人在N位置时，只能向左走走到N-1位置;
    //机器人在中间任意位置时，可以选择向左走，也可以选择向右走

    //递归参数：有N个位置，现在index位置，机器人还有rest步可以走，请返回走到p位置的方法数
    //暴力递归
    public static int process1(int N, int cur, int rest, int p) {
        //base case
        if (rest == 0) {
            return cur == p ? 1 : 0;
        }
        //rest>0
        if (cur == 1) {
            return process1(N, 2, rest - 1, p);
        }
        if (cur == N) {
            return process1(N, N - 1, rest - 1, p);
        }
        return process1(N, cur - 1, rest - 1, p) +
                process1(N, cur + 1, rest - 1, p);
    }

    public static int getAllWays1(int N, int K, int M, int P) {
        if (N < 2 || K <= 0 || M < 1 || M > N || P < 1 || P > N) {
            return 0;
        }
        return process1(N, M, K, P);
    }

    //记忆化搜索，加缓存
    //cur的变化范围是1-N
    //rest的变化范围是0-K
    public static int process2(int N, int cur, int rest, int p, int[][] dp) {
        if (dp[cur][rest] != -1) {
            return dp[cur][rest];
        }
        //否则，没有计算过
        //base case
        if (rest == 0) {
            dp[cur][rest] = cur == p ? 1 : 0;
            return dp[cur][rest];
        }
        //rest>0
        if (cur == 1) {
            dp[cur][rest] = process2(N, 2, rest - 1, p, dp);
            return dp[cur][rest];
        }
        if (cur == N) {
            dp[cur][rest] = process2(N, N - 1, rest - 1, p, dp);
            return dp[cur][rest];
        }
        dp[cur][rest] = process2(N, cur - 1, rest - 1, p, dp) +
                process2(N, cur + 1, rest - 1, p, dp);
        return dp[cur][rest];
    }

    public static int getAllWays2(int N, int K, int M, int P) {
        if (N < 2 || K <= 0 || M < 1 || M > N || P < 1 || P > N) {
            return 0;
        }
        //准备一张缓存表
        int[][] dp = new int[N + 1][K + 1];
        //dp[cur][rest]==-1认为没有计算过，需要计算
        //dp[cur][rest]!=-1认为已经计算过了，直接拿值
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        return process2(N, M, K, P, dp);
    }

    //改成动态规划
    public static int dpWays(int N, int K, int M, int P) {
        if (N < 2 || K <= 0 || M < 1 || M > N || P < 1 || P > N) {
            return 0;
        }
        int[][] dp = new int[N + 1][K + 1];
        //base case
        dp[P][0] = 1;
        //填普遍位置
        for (int rest = 1; rest <= K; rest++) {
            dp[1][rest] = dp[2][rest - 1];
            for (int cur = 2; cur < N; cur++) {
                dp[cur][rest] = dp[cur - 1][rest - 1] + dp[cur + 1][rest - 1];
            }
            dp[N][rest] = dp[N - 1][rest - 1];
        }
        return dp[M][K];
    }

    public static void main(String[] args) {
        System.out.println(getAllWays1(7, 4, 3, 5));
        System.out.println(getAllWays2(7, 4, 3, 5));
        System.out.println(dpWays(7, 4, 3, 5));
    }
}
