public class 让所有咖啡杯全部干净的最小时刻 {
    //arr[index]表示第index位员工准备洗杯子的时间点
    //认为arr[0...index-1]已经洗干净了,不需要你处理了
    //你只需要让arr[index....]变干净即可
    //a:用咖啡机洗杯子所需时间
    //b:让杯子自然干所需的时间
    //limit:咖啡机中无杯子的时刻
    public static int minTime(int[] arr, int a, int b, int index, int limit) {
        //base case
        //还剩余一个杯子时:
        if (index == arr.length - 1) {
            return Math.min(Math.max(arr[index], limit) + a, arr[index] + b);
        }
        //不止有一个杯子
        //让index位置的杯子用咖啡机来洗
        int wash = Math.max(arr[index], limit) + a;
        int next1 = minTime(arr, a, b, index + 1, wash);
        int p1 = Math.max(wash, next1);

        //让index位置的杯子挥发
        int dry = arr[index] + b;
        int next2 = minTime(arr, a, b, index + 1, limit);
        int p2 = Math.max(dry, next2);

        return Math.min(p1, p2);
    }

    //把上述代码改成动态规划
    public static int dp(int[] arr, int a, int b) {
        if (a >= b) {
            return arr[arr.length - 1] + b;
        }
        //有两个可变参数 index 和 limit 最难确定的是limit的范围
        //通过逻辑分析可以知道 limit的最大范围是 所有咖啡杯子全用机器来洗
        int maxLimit = 0;
        for (int i = 0; i < arr.length; i++) {
            maxLimit = Math.max(arr[i], maxLimit) + a;
        }
        int N = arr.length;
        int[][] dp = new int[N][maxLimit + 1];
        for (int limit = 0; limit <= maxLimit; limit++) {
            dp[N - 1][limit] = Math.min(Math.max(arr[N - 1], limit) + a, arr[N - 1] + b);
        }
        for (int index = N - 2; index >= 0; index--) {
            for (int limit = 0; limit <= maxLimit; limit++) {
                //填任意位置的limit
                int wash = Math.max(arr[index], limit) + a;
                int p1 = Integer.MAX_VALUE;
                if (wash <= maxLimit) {
                    p1 = Math.max(wash, dp[index + 1][wash]);
                }
                //让index位置的杯子挥发
                int dry = arr[index] + b;
                int next2 = dp[index + 1][limit];
                int p2 = Math.max(dry, next2);
                dp[index][limit] = Math.min(p1, p2);
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 4, 7, 7, 9, 12, 13};
        int a = 3;
        int b = 10;
        System.out.println(minTime(arr, a, b, 0, 0));
        System.out.println(dp(arr, a, b));
    }
}
