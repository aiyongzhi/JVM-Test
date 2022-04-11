public class 集合分割进阶问题 {
    //字节跳动原题
    //给定正数数组arr，请把arr中的所有数分为两个集合
    //如果arr的长度为偶数,两个集合的个数必须相同
    //如果arr的长度是奇数，两个集合的个数差不超过1
    //请尽量让两个集合的累加和接近
    //请返回最接近的情况下，较小集合的累加和

    //从左向右的尝试模型，经典背包问题
    //arr[0...i-1]已经决策好了，现在决策arr[i....]以后的决策
    //现在的背包容量是rest，必须选满k个数，请返回背包内最大累加和
    public static int smallSum1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return -1;//返回-1代表无效参数导致无效解
        }
        int sum = 0;
        for (int n : arr) {
            sum += n;
        }
        if ((arr.length & 1) == 0) {
            return process1(arr, 0, sum >> 1, arr.length / 2);
        } else {
            return process1(arr, 0, sum >> 1, arr.length / 2 + 1);
        }
    }

    public static int process1(int[] arr, int i, int rest, int k) {
        //base case
        if (i == arr.length) {
            if (arr.length % 2 == 0) {
                return k != 0 ? -1 : 0;
            } else {
                return k >= 2 ? -1 : 0;
            }
        }
        //决策i号位置
        //不要i位置的数
        int p1 = process1(arr, i + 1, rest, k);
        //要i位置的数
        int p2 = -1;
        if (arr[i] <= rest && k > 0) {
            int next = process1(arr, i + 1, rest - arr[i], k - 1);
            if (next != -1) {
                p2 = arr[i] + next;
            }
        }
        return Math.max(p1, p2);
    }

    //左神的写法本质上是一样的
    //现在来到i位置，背包的大小是rest，必须选满picks个数，请返回最大累加和
    public static int process2(int[] arr, int i, int picks, int rest) {
        //picks>0
        if (i == arr.length) {
            return picks == 0 ? 0 : -1;
        }
        int p1 = process2(arr, i + 1, picks, rest);
        int p2 = -1;
        int next = -1;
        if (arr[i] <= rest && picks > 0) {
            next = process2(arr, i + 1, picks - 1, rest - arr[i]);
        }
        if (next != -1) {
            p2 = arr[i] + next;
        }
        return Math.max(p1, p2);
    }

    public static int smallSum2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return -1;//返回-1代表无效参数导致无效解
        }
        int sum = 0;
        for (int n : arr) {
            sum += n;
        }
        if ((arr.length & 1) == 0) {
            return process2(arr, 0, arr.length >> 1, sum >> 1);
        } else {
            return Math.max(process2(arr, 0, arr.length >> 1, sum >> 1),
                    process2(arr, 0, (arr.length >> 1) + 1, sum >> 1));
        }
    }

    //改成动态规划
    public static int dpWays(int[] arr) {
        if (arr == null || arr.length < 2) {
            return -1;//返回-1代表无效参数导致无效解
        }
        int sum = 0;
        for (int n : arr) {
            sum += n;
        }
        sum >>= 1;
        int N = arr.length;
        boolean isOdd = (N & 1) != 0;
        int picks = (N & 1) == 0 ? N >> 1 : (N >> 1) + 1;
        int[][][] dp = new int[N + 1][sum + 1][picks + 1];
        for (int k = 1; k <= picks; k++) {
            //先填好每一层的最后一行
            for (int rest = 0; rest <= sum; rest++) {
                if (isOdd) {
                    dp[N][rest][k] = k >= 2 ? -1 : 0;
                } else {
                    dp[N][rest][k] = -1;
                }

            }
            for (int i = N - 1; i >= 0; i--) {
                for (int rest = 0; rest <= sum; rest++) {
                    int p1 = dp[i + 1][rest][k];
                    int p2 = -1;
                    if (arr[i] <= rest) {
                        int next = dp[i + 1][rest - arr[i]][k - 1];
                        if (next != -1) {
                            p2 = arr[i] + next;
                        }
                    }
                    dp[i][rest][k] = Math.max(p1, p2);
                }
            }
        }
        return dp[0][sum][picks];
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 3, 8, 6, 3, 9, 3, 1};
        int sum = 0;
        System.out.println(smallSum1(arr));
        System.out.println(smallSum2(arr));
        System.out.println(dpWays(arr));
        Integer o1 = 300;
        Integer o2 = 300;
        Integer o3 = 100;
        Integer o4 = 100;
        System.out.println(o1 == o2);
        System.out.println(o3 == o4);
    }
}
