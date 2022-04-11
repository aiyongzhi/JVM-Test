public class 最长回文子串 {
    //表示str[i...j]范围内最长回文字串
    //
    public static int dpWays(char[] str) {
        int N = str.length;
        int[][] dp = new int[N][N];
        //填写i<j的所有dp表
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int res = 0;
                if (isPalindrome(str, i, j)) {
                    res = Math.max(res, j + 1 - i);
                }
                res = Math.max(dp[i + 1][j], res);
                res = Math.max(dp[i][j - 1], res);
                dp[i][j] = res;
            }
        }
        return dp[0][N - 1];
    }

    public static int longestPalindrome(char[] str, int i, int j) {
        if (i >= j) {
            return 0;
        }
        int res = 0;
        if (isPalindrome(str, i, j)) {
            res = Math.max(res, j + 1 - i);
        }
        res = Math.max(longestPalindrome(str, i + 1, j), res);
        res = Math.max(longestPalindrome(str, i, j - 1), res);
        return res;
    }

    //写一个判断一个字串是否是回文的方法
    public static boolean isPalindrome(char[] str, int i, int j) {
        while (i < j) {
            if (str[i] != str[j]) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        String str = "ababace";
        char[] chs = str.toCharArray();
        int N = chs.length;
        System.out.println(longestPalindrome(chs, 0, N - 1));
        System.out.println(dpWays(chs));
    }
}
