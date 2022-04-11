public class 货币找零 {
    //arr是货币数组，其中的值都是正数，再给定一个正数aim，
    //arr中的每个值都是一张纸币，我们认为即便是值相同的两张货币也是不同的
    //请你用arr中的纸币，来拼凑出aim，请返回方法数
    public static int coinWays(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return process(0, arr, aim);
    }

    //暴力尝试
    //从左向右的尝试模型
    //递归含义:arr[0......i-1]的货币已经决策好了
    //现在你可以自由选择arr[i...]的货币要还是不要，最终返回拼凑成arr的可能性
    public static int process(int i, int[] arr, int rest) {
        //base case
        if (rest < 0) {
            return 0;
        }
        if (i == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        //当前货币可以自由选择要还是不要
        return process(i + 1, arr, rest - arr[i]) + process(i + 1, arr, rest);
    }

    public static int dpWays(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[] dp = new int[aim + 1];
        dp[0] = 1;
        int N = arr.length;
        for (int i = N - 1; i >= 0; i--) {
            for (int rest = aim; rest >= 0; rest--) {
                if (rest - arr[i] >= 0) {
                    dp[rest] += dp[rest - arr[i]];
                }
            }
        }
        return dp[aim];
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 2, 3, 2, 4};
        int aim = 10;
        System.out.println(coinWays(arr, aim));
        System.out.println(dpWays(arr, aim));
    }
}
