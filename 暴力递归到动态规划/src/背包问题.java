public class 背包问题 {
    //w数组表示N个货物的重量，v数组表示N个货物的价值
    //w[i]表示第i个货物的重量，v[i]表示第i个货物的价值
    //现在给你一个承重为bag的背包，请你返回背包能装下的货物的最大价值是多少？

    //递归含义：[0..i-1]这些货物已经决策完了，不需要你考虑
    //现在[i...]及以后的货物你可以自由选择，请返回货物的最大价值
    public static int process(int[] w, int[] v, int i, int rest) {
        //base case
        if (rest < 0) {
            return -1;
        }
        if (i == w.length) {
            return 0;
        }
        //现在可以决策i号货物
        //不要i号货物
        int p1 = process(w, v, i + 1, rest);
        //要i号货物
        int p2 = process(w, v, i + 1, rest - w[i]);
        if (p2 != -1) {
            p2 += v[i];
        }
        return Math.max(p1, p2);
    }

    public static int maxValue(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0 || bag <= 0) {
            return 0;
        }
        return process(w, v, 0, bag);
    }

    //动态规划
    public static int dpWays(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0 || bag <= 0) {
            return 0;
        }
        int N = w.length;
        //空间压缩
        int[] dp = new int[bag + 1];
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = bag; rest >= 0; rest--) {
                if (rest - w[index] >= 0) {
                    dp[rest] = Math.max(dp[rest], v[index] +
                            dp[rest - w[index]]);
                }
            }
        }
        return dp[bag];
    }

    public static void main(String[] args) {
        int[] w = {1, 3, 4, 5, 6, 7, 1, 3, 2};
        int[] v = {3, 4, 7, 2, 12, 15, 1, 3, 9};
        int bag = 10;
        System.out.println(maxValue(w, v, bag));
        System.out.println(dpWays(w, v, bag));
    }
}
