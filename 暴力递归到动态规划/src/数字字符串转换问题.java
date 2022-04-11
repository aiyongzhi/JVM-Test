public class 数字字符串转换问题 {
    //现在1->A 2->B 3->C 4->D.....26->Z以此类推
    //现在给定你一个字符串str，str只由数字字符构成，现在需要将数字字符串，转换为字母对应的字符串
    //请返回转换的方法数
    //例如"111" 可以转换为"AAA"，”KA“或者"AK" 返回3

    //从左向右的尝试模型
    //现在str[0...i-1]已经转换成字符了，不需要你考虑
    //现在str[i...]可以任意转换，请返回方法数
    public static int process(char[] str, int i) {
        //base case
        if (i == str.length) {
            return 1;
        }
        if (str[i] == '0') {
            return 0;
        }
        //str[i]!='0'
        if (str[i] == '1') {
            int res = process(str, i + 1);
            if (i + 1 < str.length) {
                res += process(str, i + 2);
            }
            return res;
        }
        if (str[i] == '2') {
            int res = process(str, i + 1);
            if (i + 1 < str.length && (str[i + 1] >= '0' && str[i] <= '6')) {
                res += process(str, i + 2);
            }
            return res;
        }
        //str[i]>='3'
        return process(str, i + 1);
    }

    public static int getAllWays(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return process(str.toCharArray(), 0);
    }

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
                dp[i] = 0;
            } else {
                dp[i] = dp[i + 1];
                if (i + 1 < N && ((chs[i] - '0') * 10 + chs[i + 1] - '0') < 27) {
                    dp[i] += dp[i + 2];
                }
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        String str = "13456345454610";
        System.out.println(getAllWays(str));
        System.out.println(dpWays(str));
    }
}
