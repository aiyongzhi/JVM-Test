public class 找零钱的方法数 {
    //现在有N种硬币，其面值为arr[i] 每种硬币你可以使用无限次
    //再给你一个总钱数count 问用硬币拼凑成count的方法数

    //尝试模型：从左向右的线性尝试模型
    //index 表示当前决定的硬币
    //通过枚举 举出面值为arr[index]面值的硬币用多少次
    //
    public static int getAll1(int[] arr, int count) {
        if (arr == null || arr.length == 0 || count <= 0) {
            return 0;
        }
        return process(arr, 0, count);
    }

    //rest表示当前还剩余需要拼的钱数
    public static int process(int[] arr, int index, int rest) {
        //base case
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        int res = 0;
        for (int i = 0; i <= (rest / arr[index]); i++) {
            res += process(arr, index + 1, rest - (arr[index] * i));
        }
        return res;
    }

    public static int dpWays3(int[] arr, int count) {
        if (arr == null || arr.length == 0 || count <= 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][count + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= count; rest++) {
                int res = 0;
                for (int i = 0; i < (rest / arr[index]); i++) {
                    res += dp[index + 1][rest - (arr[index] * i)];
                }
                dp[index][rest] = res;
            }
        }
        return dp[0][count];
    }

    //记忆化搜索
    public static int getAll3(int[] arr, int count) {
        if (arr == null || arr.length == 0 || count <= 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][count + 1];
        //默认所有全没填过，全部初始化为-1
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= count; j++) {
                dp[i][j] = -1;
            }
        }
        return dpProcess(arr, 0, count, dp);
    }

    public static int dpProcess(int[] arr, int index, int rest, int[][] dp) {
        if (dp[index][rest] != -1) {
            return dp[index][rest];
        }
        if (index == arr.length) {
            dp[index][rest] = 0;
            return 0;
        }
        int res = 0;
        for (int i = 0; i < (rest / arr[index]); i++) {
            res += process(arr, index + 1, rest - (arr[index] * i));
        }
        dp[index][rest] = res;
        return dp[index][rest];
    }

    //改成动态规划 两个可变参数 二维动态规划
    //动态规划
    public static int dpWays(int[] arr, int count) {
        if (arr == null || arr.length == 0 || count <= 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][count + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= count; rest++) {
                //在这里只要填好 dp[index][rest]=? 就成功了
                //枚举过程往往可以再优化，一定要画dp图 列出枚举情况进行分析
                dp[index][rest] = dp[index + 1][rest];
                if (rest - arr[index] >= 0) {
                    dp[index][rest] += dp[index][rest - arr[index]];
                }
            }
        }
        return dp[0][count];
    }

    public static int getAll2(int[] arr, int count) {
        if (arr == null || arr.length == 0 || count < 0) {
            return 0;
        }
        int[][] dp = new int[arr.length + 1][count + 1];
        for (int row = 0; row <= arr.length; row++) {
            for (int col = 0; col <= count; col++) {
                dp[row][col] = -1;
            }
        }
        return dpWays2(arr, 0, count, dp);
    }

    //线性压缩优化后的
    public static int dpWays2(int[] arr, int index, int rest, int[][] dp) {
        if (dp[index][rest] != -1) {
            return dp[index][rest];
        }
        if (arr.length == index) {
            dp[index][rest] = rest == 0 ? 1 : 0;
            return dp[index][rest];
        }
        //有硬币选的
        int res = 0;
        for (int i = 0; i <= (rest / arr[index]); i++) {
            res += process(arr, index + 1, rest - (i * arr[index]));
        }
        dp[index][rest] = res;
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5};
        System.out.println(getAll1(arr, 8));
        System.out.println(dpWays(arr, 8));
        System.out.println(getAll2(arr, 8));
        System.out.println(process(arr, 0, 8));
    }
}
