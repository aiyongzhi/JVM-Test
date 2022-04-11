public class 货币找零所需要的最小张数 {
    //arr是面值数组，arr中全是正数且无重复值，再给定一个正数aim
    //arr[i]:表示面值为arr[i]的纸币有无穷多张
    //请返回拼凑出aim的最小货币数

    //从左向右的尝试模型
    //递归含义:money[i...]以后的货币可以自由选择张数,请返回拼凑出rest的最小张数
    public static int minSheet(int[] money, int aim) {
        if (money == null || money.length == 0 || aim <= 0) {
            return 0;
        }
        int ans = process(0, aim, money);
        return ans == Integer.MAX_VALUE ? -1 : ans;//返回-1代表无论如何都拼凑不出aim
    }

    public static int process(int i, int rest, int[] money) {
        //rest>=0
        if (rest == 0) {
            return 0;
        }
        //rest>0
        if (i == money.length) {
            return Integer.MAX_VALUE;
        }
        //rest>0&&还有货币可以自由选择
        int ans = Integer.MAX_VALUE;
        for (int zhang = 0; zhang * money[i] <= rest; zhang++) {
            int next = process(i + 1, rest - zhang * money[i], money);
            if (next != Integer.MAX_VALUE) {
                ans = Math.min(ans, next + zhang);
            }
        }
        return ans;
    }

    //改成动态规划
    public static int dpWays1(int[] money, int aim) {
        if (money == null || money.length == 0 || aim <= 0) {
            return 0;
        }
        int N = money.length;
        int[][] dp = new int[N + 1][aim + 1];
        for (int rest = 1; rest <= aim; rest++) {
            dp[N][rest] = Integer.MAX_VALUE;
        }
        for (int i = N - 1; i >= 0; i--) {
            for (int rest = 1; rest <= aim; rest++) {
                int ans = Integer.MAX_VALUE;
                for (int zhang = 0; zhang * money[i] <= rest; zhang++) {
                    int next = dp[i + 1][rest - zhang * money[i]];
                    if (next != Integer.MAX_VALUE) {
                        ans = Math.min(ans, next + zhang);
                    }
                }
                dp[i][rest] = ans;
            }
        }
        int res = dp[0][aim];
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    //进行枚举行为的优化
    public static int dpWays2(int[] money, int aim) {
        if (money == null || money.length == 0 || aim <= 0) {
            return 0;
        }
        int N = money.length;
        int[][] dp = new int[N + 1][aim + 1];
        for (int rest = 1; rest <= aim; rest++) {
            dp[N][rest] = Integer.MAX_VALUE;
        }
        for (int i = N - 1; i >= 0; i--) {
            for (int rest = 1; rest <= aim; rest++) {
                dp[i][rest] = dp[i + 1][rest];
                if (rest - money[i] >= 0) {
                    int next = dp[i][rest - money[i]];
                    if (next != Integer.MAX_VALUE) {
                        dp[i][rest] = Math.min(dp[i][rest], next + 1);
                    }
                }
            }
        }
        int res = dp[0][aim];
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    //再优化，进行空间压缩
    public static int dpWays3(int[] money, int aim) {
        if (money == null || money.length == 0 || aim <= 0) {
            return 0;
        }
        int N = money.length;
        int[] dp = new int[aim + 1];
        for (int rest = 1; rest <= aim; rest++) {
            dp[rest] = Integer.MAX_VALUE;
        }
        for (int i = N - 1; i >= 0; i--) {
            for (int rest = 1; rest <= aim; rest++) {
                if (rest - money[i] >= 0) {
                    int next = dp[rest - money[i]];
                    if (next != Integer.MAX_VALUE) {
                        dp[rest] = Math.min(dp[rest], next + 1);
                    }
                }
            }
        }
        int res = dp[aim];
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    public static void main(String[] args) {
        int[] arr = {2, 5, 10, 3, 7, 6, 9};
        int aim = 1008;
        //System.out.println(minSheet(arr,aim));
        System.out.println(dpWays1(arr, aim));
        System.out.println(dpWays2(arr, aim));
        System.out.println(dpWays3(arr, aim));
    }
}
