public class 最长公共子序列 {
    //左神是直接通过动态规划来写的
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

    //最长公共子序列
    public static int longestCommonSubsequence1(char[] str1, char[] str2) {
        if (str1 == null || str1.length == 0 || str2 == null || str2.length == 0) {
            return 0;
        }
        int len1 = str1.length;
        int len2 = str2.length;
        int[][] dp = new int[len1][len2];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int i = 1; i < len2; i++) {
            dp[0][i] = (str1[0] == str2[i] || dp[0][i - 1] == 1) ? 1 : 0;
        }
        for (int i = 1; i < len1; i++) {
            dp[i][0] = (str1[i] == str2[0] || dp[i - 1][0] == 1) ? 1 : 0;
        }

        //填普通位置
        for (int i = 1; i < len1; i++) {
            for (int j = 1; j < len2; j++) {
                if (str1[i] == str2[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[len1 - 1][len2 - 1];
    }

    public static void main(String[] args) {
        String s1 = "13ad";
        String s2 = "2abdefegwge";
        System.out.println(longestCommonSubsequence(s1, s2));
        System.out.println(longestCommonSubsequence1(s1.toCharArray(), s2.toCharArray()));
    }
}
