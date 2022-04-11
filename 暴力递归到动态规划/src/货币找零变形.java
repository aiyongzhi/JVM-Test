import java.util.HashMap;

public class 货币找零变形 {
    //给定一个正数数组arr,arr中表示的是货币的面值
    //arr[i]表示一张面值为arr[i]的货币
    //我们认为面值相同的纸币是没有区别的
    //再给定一个变量aim,请返回拼凑出aim的方法数
    //例如arr[1,2,1,1,2,2,1] aim=4
    //1+1+1+1 1+1+2 2+2 共三种可能
    public static int[][] getMoneysAndCountArray(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }
        int N = map.size();
        int[] money = new int[N];
        int[] count = new int[N];
        int index = 0;
        for (int num : map.keySet()) {
            money[index] = num;
            count[index++] = map.get(num);
        }
        int[][] ans = {money, count};
        return ans;
    }

    public static int getAllWays1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[][] infoArray = getMoneysAndCountArray(arr);
        int[] money = infoArray[0];
        int[] count = infoArray[1];
        return process(money, count, 0, aim);
    }

    //暴力递归money面值数组，已经去重
    //count[i]表示面值为money[i]的货币有count[i]张
    //请用money[i....]的纸币张数任意选，请返回拼凑出rest的方法数
    public static int process(int[] money, int[] count, int i, int rest) {
        //base case
        if (i == money.length) {
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        for (int zhang = 0; zhang <= count[i] && (zhang * money[i] <= rest); zhang++) {
            ways += process(money, count, i + 1, rest - zhang * money[i]);
        }
        return ways;
    }

    //改成动态规划
    public static int dpWays1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[][] infoArray = getMoneysAndCountArray(arr);
        int[] money = infoArray[0];
        int[] count = infoArray[1];
        int N = money.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int i = N - 1; i >= 0; i--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ways = 0;
                for (int zhang = 0; zhang <= count[i] && (zhang * money[i] <= rest); zhang++) {
                    ways += dp[i + 1][rest - zhang * money[i]];
                }
                dp[i][rest] = ways;
            }
        }
        return dp[0][aim];
    }

    //将动态规划的枚举行为优化
    //枚举行为有两种情况：1：当前位置能枚举完所有张数，没有被rest限制，但被张数限制
    //2:当前位置枚举不完所有张数，直接被rest卡死
    public static int dpWays2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[][] infoArray = getMoneysAndCountArray(arr);
        int[] money = infoArray[0];
        int[] count = infoArray[1];
        int N = money.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int i = N - 1; i >= 0; i--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[i][rest] = dp[i + 1][rest];
                if (rest - money[i] >= 0) {
                    dp[i][rest] += dp[i][rest - money[i]];
                }
                if (rest - (count[i] + 1) * money[i] >= 0) {
                    dp[i][rest] -= dp[i + 1][rest - (count[i] + 1) * money[i]];
                }
            }
        }
        return dp[0][aim];
    }

    //空间压缩
    public static int dpWays3(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[][] infoArray = getMoneysAndCountArray(arr);
        int[] money = infoArray[0];
        int[] count = infoArray[1];
        int N = money.length;
        int[][] dp = new int[2][aim + 1];
        dp[N % 2][0] = 1;
        for (int i = N - 1; i >= 0; i--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[i % 2][rest] = dp[(i + 1) % 2][rest];
                if (rest - money[i] >= 0) {
                    dp[i % 2][rest] += dp[i % 2][rest - money[i]];
                }
                if (rest - (count[i] + 1) * money[i] >= 0) {
                    dp[i % 2][rest] -= dp[(i + 1) % 2][rest - (count[i] + 1) * money[i]];
                }
            }
        }
        return dp[0][aim];
    }

    public static void main(String[] args) {
        int[] arr = {1, 4, 6, 2, 4, 1, 5, 3, 5, 7, 3, 2, 4, 5, 6, 7};
        int aim = 25;
        System.out.println(dpWays1(arr, aim));
        System.out.println(dpWays2(arr, aim));
        System.out.println(dpWays3(arr, aim));
        System.out.println(getAllWays1(arr, aim));
    }
}
