public class 最长回文子序列 {
    //最长回文子序列
    //回文子序列 正着读和逆着读一样
    //思路：将str逆序，求逆序前与逆序后的最长公共子序列 就是最长回文子序列
    public static String reverse(String str) {
        char[] chs = str.toCharArray();
        int N = chs.length;
        int L = 0;
        int R = N - 1;
        while (L < R) {
            char tmp = chs[L];
            chs[L] = chs[R];
            chs[R] = tmp;
            L++;
            R--;
        }
        return String.valueOf(chs);
    }

    public static int longestCommonSubsequence(String s1, String s2) {
        return dpWays(s1.toCharArray(), s2.toCharArray());
    }

    public static int dpWays(char[] str1, char[] str2) {
        if (str1 == null || str1.length == 0 || str2 == null || str2.length == 0) {
            return 0;
        }
        int n1 = str1.length;
        int n2 = str2.length;
        int[][] dp = new int[n1][n2];
        //把第一行初始化
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int i = 1; i < n2; i++) {
            dp[0][i] = (str1[0] == str2[i] || dp[0][i - 1] == 1) ? 1 : 0;
        }
        //把每一列初始化
        for (int i = 1; i < n1; i++) {
            dp[i][0] = (str1[i] == str2[0] || dp[i - 1][0] == 1) ? 1 : 0;
        }
        //填普遍位置的格子
        for (int i = 1; i < n1; i++) {
            for (int j = 1; j < n2; j++) {
                //只需填好普遍位置dp[i][j]的值即可
                //先假设str[i]和str2[j] 均不设最长公共子序列的结尾
                if (str1[i] != str2[j]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                } else {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp[n1 - 1][n2 - 1];
    }

    public static int longestPalindromeSubseq(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        String newStr = new String(str);
        String reverseStr = reverse(str);
        return longestCommonSubsequence(newStr, reverseStr);
    }

    public static void main(String[] args) {
        String str = "ab12c3g21";
        System.out.println(longestPalindromeSubseq(str));
    }
}
