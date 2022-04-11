public class 货币找零进阶 {
    //货币找零进阶：arr表是一个货币的面值数组，arr中的值全是正数且没有重复值
    //每一种货币都有无穷多张，请放回拼凑出aim的方法数
    //解题步骤：1：通过尝试模型想暴力递归

    //暴力递归：从左向右的尝试模型
    //arr[0......i-1]已经决策好了
    //现在arr[i...]以后所有的货币你可以任意选择，请返回拼凑出rest的可能性
    public static int coinWays(int[] arr, int ans) {
        if (arr == null || arr.length == 0 || ans < 0) {
            return 0;
        }
        return process(0, arr, ans);
    }

    public static int process(int i, int[] arr, int rest) {
        if (i == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        //rest>0,且有货币
        int ans = 0;
        for (int zhang = 0; zhang * arr[i] <= rest; zhang++) {
            ans += process(i + 1, arr, rest - zhang * arr[i]);
        }
        return ans;
    }

    //动态规划
    public static int dpWays1(int[] arr, int ans) {
        if (arr == null || arr.length == 0 || ans < 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][ans + 1];
        dp[N][0] = 1;
        for (int i = N - 1; i >= 0; i--) {
            for (int rest = 0; rest <= ans; rest++) {
                int ways = 0;
                for (int zhang = 0; zhang * arr[i] <= rest; zhang++) {
                    ways += dp[i + 1][rest - zhang * arr[i]];
                }
                dp[i][rest] = ways;
            }
        }
        return dp[0][ans];
    }

    //枚举行为往往可以优化
    public static int dpWays2(int[] arr, int ans) {
        if (arr == null || arr.length == 0 || ans < 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][ans + 1];
        dp[N][0] = 1;
        for (int i = N - 1; i >= 0; i--) {
            for (int rest = 0; rest <= ans; rest++) {
                dp[i][rest] = dp[i + 1][rest];
                if (rest - arr[i] >= 0) {
                    dp[i][rest] += dp[i][rest - arr[i]];
                }
            }
        }
        return dp[0][ans];
    }

    //进行空间压缩
    public static int dpWays3(int[] arr, int ans) {
        if (arr == null || arr.length == 0 || ans < 0) {
            return 0;
        }
        int N = arr.length;
        int[] dp = new int[ans + 1];
        dp[0] = 1;
        for (int i = N - 1; i >= 0; i--) {
            for (int rest = 0; rest <= ans; rest++) {
                if (rest - arr[i] >= 0) {
                    dp[rest] += dp[rest - arr[i]];
                }
            }
        }
        return dp[ans];
    }

    public static void main(String[] args) {
        int[] arr = {2, 4, 10, 20};
        int ans = 820;
        System.out.println(dpWays1(arr, ans));
        System.out.println(dpWays2(arr, ans));
        System.out.println(dpWays3(arr, ans));
        System.out.println(coinWays(arr, ans));
    }
}
