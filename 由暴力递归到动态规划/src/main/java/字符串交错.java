public class 字符串交错 {
    //用s1和s2交错拼成s3，需要保证在s3中s1和s2的相对顺序不变。
    //请返回s1和s2能否交拼成s3
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null || s1.length() == 0) {
            return s2.equals(s3);
        }
        if (s2 == null || s2.length() == 0) {
            return s1.equals(s3);
        }
        //s1和s2 都不为空
        int len1 = s1.length();
        int len2 = s2.length();
        int len3 = s3.length();
        if (len1 + len2 != len3) {
            return false;
        }
        boolean[][] dp = new boolean[len1 + 1][len2 + 1];
        dp[0][0] = true;
        char[] chs1 = s1.toCharArray();
        char[] chs2 = s2.toCharArray();
        char[] chs3 = s3.toCharArray();
        //先填第一行和第一列
        for (int i = 1; i <= len2; i++) {
            if (chs2[i - 1] != chs3[i - 1]) {
                break;
            }
            dp[0][i] = true;
        }
        for (int i = 1; i <= len1; i++) {
            if (chs1[i - 1] != chs3[i - 1]) {
                break;
            }
            dp[i][0] = true;
        }
        //填普遍位置
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                //只需要填好dp[i][j]就足够了
                if ((chs3[i + j - 1] == chs1[i - 1] && dp[i - 1][j]) ||
                        (chs3[i + j - 1] == chs2[j - 1] && dp[i][j - 1])) {
                    dp[i][j] = true;
                }
            }
        }
        return dp[len1][len2];
    }

    public static boolean isInterleave1(String str1, String str2, String ans) {
        if (str1 == null || str1.length() == 0) {
            return str2.equals(ans);
        }
        if (str2 == null || str2.length() == 0) {
            return str1.equals(ans);
        }
        //填满一行 一列
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        char[] res = ans.toCharArray();
        boolean[][] dp = new boolean[s1.length + 1][s2.length + 1];
        for (int i = 1; i <= s2.length; i++) {
            if (s2[i - 1] != res[i - 1]) {
                break;
            }
            dp[0][i] = true;
        }
        for (int i = 1; i <= s1.length; i++) {
            if (s1[i - 1] != res[i - 1]) {
                break;
            }
            dp[i][0] = true;
        }
        //填普遍位置
        for (int i = 1; i <= s1.length; i++) {
            for (int j = 1; j <= s2.length; j++) {
                if ((s1[i - 1] == res[i + j - 1] && dp[i - 1][j]) ||
                        (s2[j - 1] == res[i + j - 1] && dp[i][j - 1])) {
                    dp[i][j] = true;
                }
            }
        }
        return dp[s1.length][s2.length];
    }
}
