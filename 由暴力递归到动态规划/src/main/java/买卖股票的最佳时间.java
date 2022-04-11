public class 买卖股票的最佳时间 {
    public int maxProfit(int[] prices) {
        //用动态规划思想:可以斜率优化：小烧脑
        int N = prices.length;
        //dp含义在0...i上还剩rest交易次数时，能获得的最大利润
        int[][] dp = new int[N][3];
        //一列一列的填数据：新奇的填数据原则
        for (int rest = 1; rest < 3; rest++) {
            //填这一列
            int bestChoose = Math.max(dp[1][rest - 1] - prices[1], dp[0][rest - 1] - prices[0]);
            dp[1][rest] = Math.max(dp[0][rest], bestChoose + prices[1]);
            for (int i = 2; i < N; i++) {
                bestChoose = Math.max(dp[i][rest - 1] - prices[i], bestChoose);
                dp[i][rest] = Math.max(dp[i - 1][rest], bestChoose + prices[i]);
            }
        }
        return dp[N - 1][2];
    }
}
