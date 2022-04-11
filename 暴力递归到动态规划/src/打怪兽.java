public class 打怪兽 {
    //给你三个参数：N,M,K
    //怪兽最初有N滴血，等着英雄来砍自己
    //英雄的每次砍击，都会让怪兽掉[0~M]滴血，
    //具体掉多少血？等概率的返回[0~M]间的数值
    //英雄砍满砍K次，请问K次把怪兽砍死的概率

    //从左向右的尝试模型
    //递归含义：现在是第i次砍击，总共K次，怪兽还剩rest滴血
    //请返回英雄在[i....]次砍击能把怪物击杀的方法数
    //剪枝和不剪枝答案不一致
    //如果是最多砍k次，砍死了就不砍了，就一定要剪枝
    //如果是砍满k刀，砍死的概率就一定直接不能剪枝
    public static double kill(int N, int M, int K) {
        if (N <= 0 || M <= 0 || K <= 0) {
            return -1;//返回-1代表无效解
        }
        long all = (long) Math.pow(M + 1, K);
        long kill = process1(K, N, M);
        System.out.println(kill);
        return (double) kill / (double) all;
    }

    public static long process1(int K, int rest, int M) {
        //base case
        if (K == 0) {
            return rest <= 0 ? 1 : 0;
        }
        //K>0
        if (rest <= 0) {
            return (long) Math.pow(M + 1, K);
        }
        //枚举所有的伤害
        long ans = 0;
        for (int hurt = 0; hurt <= M; hurt++) {
            ans += process1(K - 1, rest - hurt, M);
        }
        return ans;
    }

    public static long process2(int K, int rest, int M) {
        //base case
        //rest>0
        if (K == 0) {
            return rest <= 0 ? 1 : 0;
        }
        //枚举所有的伤害
        long ans = 0;
        for (int hurt = 0; hurt <= M; hurt++) {
            ans += process2(K - 1, rest - hurt, M);
        }
        return ans;
    }

    //改成动态规划
    public static double dpWays1(int N, int M, int K) {
        if (N <= 0 || M <= 0 || K <= 0) {
            return -1;//返回-1代表无效解
        }
        long all = (long) Math.pow(M + 1, K);
        long[][] dp = new long[K + 1][N + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= K; i++) {
            for (int rest = 0; rest <= N; rest++) {
                long ans = 0;
                for (int hurt = 0; hurt <= M && hurt < rest; hurt++) {
                    ans += dp[i - 1][rest - hurt];
                }
                dp[i][rest] = ans + (M >= rest ? ((M - rest + 1) * (long) Math.pow(M + 1, i - 1)) : 0);
            }
        }
        long kill = dp[K][N];
        return (double) kill / (double) all;
    }

    //枚举行为再优化
    public static double dpWays2(int N, int M, int K) {
        if (N <= 0 || M <= 0 || K <= 0) {
            return -1;//返回-1代表无效解
        }
        long all = (long) Math.pow(M + 1, K);
        long[][] dp = new long[K + 1][N + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= K; i++) {
            dp[i][0] = (M + 1) * (long) Math.pow(M + 1, i - 1);
            for (int rest = 1; rest <= N; rest++) {
                dp[i][rest] = dp[i][rest - 1] + dp[i - 1][rest];
                if (rest - 1 - M <= 0) {
                    dp[i][rest] -= (long) Math.pow(M + 1, i - 1);
                } else {
                    dp[i][rest] -= dp[i - 1][rest - 1 - M];
                }
            }
        }
        long kill = dp[K][N];
        return (double) kill / (double) all;
    }

    public static void main(String[] args) {
        int N = 200;
        int M = 20;
        int K = 15;

        long start = System.currentTimeMillis();
        System.out.println(dpWays1(N, M, K));
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        start = System.currentTimeMillis();
        System.out.println(dpWays2(N, M, K));
        end = System.currentTimeMillis();
        System.out.println(end - start);

        //暴力递归
        /*start=System.currentTimeMillis();
        System.out.println(kill(N,M,K));
        end=System.currentTimeMillis();
        System.out.println(end-start);*/
    }
}
