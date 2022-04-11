public class 最近和的集合分割 {
    //给定一个正数数组arr,
    //请把arr拆分为两个集合，尽量让两个集合的累加和接近
    //请返回在最接近的情况下，较小集合的累加和
    public static class Info {
        public int smallSum;//较小集合的累加和
        public int distance;//两个集合的差值

        public Info(int smallSum, int distance) {
            this.smallSum = smallSum;
            this.distance = distance;
        }
    }

    //从左向右的尝试模型
    //递归含义：arr[0....i-1]已经决策好了，以前的决策导致集合A的累加和是sumA，集合B的累加和是sumB
    //arr[i...]及其以后所有数字可以自由选择归属,请返回差值最小集合的累加和
    public static Info process1(int i, int[] arr, int sumA, int sumB) {
        //base case
        if (i == arr.length) {
            int min = Math.min(sumA, sumB);
            int distance = Math.abs(sumA - sumB);
            return new Info(min, distance);
        }
        //还有数字可以选择
        //选择arr[i]进集合A
        Info p1 = process1(i + 1, arr, sumA + arr[i], sumB);
        //选择arr[i]进集合B
        Info p2 = process1(i + 1, arr, sumA, sumB + arr[i]);
        return p1.distance <= p2.distance ? p1 : p2;
    }

    public static int smallSum1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Info all = process1(0, arr, 0, 0);
        return all.smallSum;
    }
    //上述问题明显可以看成一个背包问题
    //因为较小集合的累加和必然不会超过数组总和的一半
    //因此我们把较小集合抽象成一个背包，我们可以自由选择数字进不进背包
    //请返回背包承载量能接受的情况下，背包内数字的最大累加和


    //递归含义arr[0...i-1]已经决策好了，arr[i...]的数据你可以任意决策
    //背包还剩rest容量，请返回在不超重情况下，背包内最大累加和
    public static int process2(int[] arr, int i, int rest) {
        //在上游决策背包不超重
        //base case
        if (i == arr.length) {
            return 0;
        }
        //选择不进背包
        int p1 = process2(arr, i + 1, rest);
        //选择进背包
        int p2 = 0;
        if (arr[i] <= rest) {
            p2 = arr[i] + process2(arr, i + 1, rest - arr[i]);
        }
        return Math.max(p1, p2);
    }

    public static int smallSum2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int sum = 0;
        for (int n : arr) {
            sum += n;
        }
        return process2(arr, 0, sum >> 1);
    }

    //很明显存在重复解
    //改成动态规划
    public static int dpWays1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return -1;//表示无效数据
        }
        int N = arr.length;
        int sum = 0;
        for (int n : arr) {
            sum += n;
        }
        int[][] dp = new int[N + 1][(sum >> 1) + 1];
        for (int i = N - 1; i >= 0; i--) {
            for (int rest = 0; rest <= (sum >> 1); rest++) {
                int p1 = dp[i + 1][rest];
                int p2 = 0;
                if (arr[i] <= rest) {
                    p2 = arr[i] + dp[i + 1][rest - arr[i]];
                }
                dp[i][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][sum >> 1];
    }

    //空间压缩优化
    public static int dpWays2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return -1;//表示无效数据
        }
        int N = arr.length;
        int sum = 0;
        for (int n : arr) {
            sum += n;
        }
        int[] dp = new int[(sum >> 1) + 1];
        for (int i = N - 1; i >= 0; i--) {
            for (int rest = (sum >> 1); rest >= 0; rest--) {
                int p1 = dp[rest];
                int p2 = 0;
                if (arr[i] <= rest) {
                    p2 = arr[i] + dp[rest - arr[i]];
                }
                dp[rest] = Math.max(p1, p2);
            }
        }
        return dp[sum >> 1];
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 2, 5, 8, 7};
        int sum = 0;
        for (int n : arr) {
            sum += n;
        }
        System.out.println(sum);
        System.out.println(smallSum1(arr));
        System.out.println(smallSum2(arr));
        System.out.println(dpWays1(arr));
        System.out.println(dpWays2(arr));
    }
}
