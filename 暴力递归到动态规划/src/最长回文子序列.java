public class 最长回文子序列 {
    //解法一：间接求：求原字符串和它的逆序的最长公共子序列
    //解法二：直接求：范围上尝试模型
    public static int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        return process(s.toCharArray(), 0, s.length() - 1);
    }

    //递归含义：请返回str[L...R]范围内的最长回文子序列的长度
    public static int process(char[] str, int L, int R) {
        //base case
        if (L > R) {
            return 0;
        }
        if (L == R) {
            return 1;
        }
        //L<R
        int ans = 0;
        if (str[L] == str[R]) {
            ans = 2 + process(str, L + 1, R - 1);
        } else {
            int p1 = process(str, L, R - 1);
            int p2 = process(str, L + 1, R);
            ans = Math.max(p1, p2);
        }
        return ans;
    }

    //暴力递归改成动态规划
    public static int dpWays(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[][] dp = new int[N][N];
        //填好dp表
        for (int i = 0; i < N; i++) {
            dp[i][i] = 1;
        }
        for (int i = 1; i < N; i++) {
            int L = 0;
            int R = i;
            while (R < N) {
                int ans = 0;
                if (str[L] == str[R]) {
                    ans = 2 + dp[L + 1][R - 1];
                } else {
                    int p1 = dp[L][R - 1];
                    int p2 = dp[L + 1][R];
                    ans = Math.max(p1, p2);
                }
                dp[L][R] = ans;
                L++;
                R++;
            }
        }
        return dp[0][N - 1];
    }

}
