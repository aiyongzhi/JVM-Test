public class 数字转换成字符串全部方法数 {
    //把一个数字字符串转换为字母字符串的全部方法数
    public static int getAllNumbers(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return process(str.toCharArray(), 0);
    }

    //从左向右的尝试模型
    //在尝试到i位置时，chs[0....index-1]都已经转换好了
    public static int process(char[] chs, int i) {
        //写base case 把能转换完的标为合格并终止
        if (i == chs.length) {
            return 1;
        }
        //
        if (chs[i] == '0') {
            return 0;
        }
        //剩下来的数据都能够正常进行转换
        //1:先考虑能够两个字符一起转换的
        if (chs[i] == '1') {
            int res = process(chs, i + 1);
            if (i + 1 < chs.length) {
                res += process(chs, i + 2);
            }
            return res;
        }
        if (chs[i] == '2') {
            int res = process(chs, i + 1);
            if (i + 1 < chs.length && chs[i + 1] >= '0' && chs[i + 1] <= '6') {
                res += process(chs, i + 2);
            }
            return res;
        }
        return process(chs, i + 1);
    }

    //动态规划写法
    public static int dpWays(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int N = str.length();
        char[] chs = str.toCharArray();
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int i = N - 1; i >= 0; i--) {
            if (chs[i] == '0') {
                dp[i] = 1;
            } else if (chs[i] == '1') {
                dp[i] = dp[i + 1];
                if (i + 1 < chs.length) {
                    dp[i] += dp[i + 2];
                }
            } else if (chs[i] == '2') {
                dp[i] = dp[i + 1];
                if (i + 1 < chs.length && chs[i + 1] >= '0' && chs[i + 1] <= '6') {
                    dp[i] += dp[i + 2];
                }
            } else {
                dp[i] = dp[i + 1];
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        String str = "12315";
        System.out.println(getAllNumbers(str));
        System.out.println(dpWays(str));
    }
}
