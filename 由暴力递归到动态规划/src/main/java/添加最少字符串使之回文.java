public class 添加最少字符串使之回文 {
    //题目描述 给定一个字符串 请问添加字符使之构成回文
    //所需要的最少添加字符的个数
    //由暴力递归到动态规划

    //递归含义：让str[L....R]上回文所添加最少字符数
    public static int process(char[] str, int L, int R) {
        if (L >= R) {
            return 0;
        }
        if (L + 1 == R) {
            return str[L] == str[R] ? 0 : 1;
        }
        //L..R有两个以上元素
        int res = 0;
        if (str[L] == str[R]) {
            res = process(str, L + 1, R - 1);
        } else {
            res = Math.min(process(str, L + 1, R), process(str, L, R - 1)) + 1;
        }
        return res;
    }

    public static int process2(char[] str, int L, int R) {
        //base case
        if (L == R) {
            return 0;
        }
        if (str[L] == str[R]) {
            return process2(str, L + 1, R - 1);
        }
        return Math.min(process2(str, L + 1, R), process2(str, L, R - 1)) + 1;

    }

    //动态规划版本
    public static int dpWays(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int N = str.length();
        char[] chs = str.toCharArray();
        int[][] dp = new int[N][N];
        //填第二个对角线
        int i = 0;
        int j = 1;
        while (i < N && j < N) {
            dp[i][j] = chs[i] == chs[j] ? 0 : 1;
            i++;
            j++;
        }
        for (int k = 2; k < N; k++) {
            //填数据规则要一个斜线一个斜线的填数据
            int R = k;
            int L = 0;
            while (R < N && L < N) {
                int res = 0;
                if (chs[L] == chs[R]) {
                    res = dp[L + 1][R - 1];
                } else {
                    res = Math.min(dp[L + 1][R], dp[L][R - 1]) + 1;
                }
                dp[L][R] = res;
                L++;
                R++;
            }
        }
        return dp[0][N - 1];
    }

    //如果需要打印其路径如何操作
    public static int[][] getDp(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        int N = str.length();
        char[] chs = str.toCharArray();
        int[][] dp = new int[N][N];
        //填第二个对角线
        int i = 0;
        int j = 1;
        while (i < N && j < N) {
            dp[i][j] = chs[i] == chs[j] ? 0 : 1;
            i++;
            j++;
        }
        for (int k = 2; k < N; k++) {
            //填数据规则要一个斜线一个斜线的填数据
            int R = k;
            int L = 0;
            while (R < N && L < N) {
                int res = 0;
                if (chs[L] == chs[R]) {
                    res = dp[L + 1][R - 1];
                } else {
                    res = Math.min(dp[L + 1][R], dp[L][R - 1]) + 1;
                }
                dp[L][R] = res;
                L++;
                R++;
            }
        }
        return dp;
    }

    public static String getPalindrome(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        int[][] dp = getDp(str);
        char[] chas = str.toCharArray();
        int N = chas.length;
        char[] res = new char[N + dp[0][N - 1]];
        int L = 0;
        int R = N - 1;
        int restL = 0;
        int restR = res.length - 1;
        while (L <= R) {
            if (chas[L] == chas[R]) {
                res[restL++] = chas[L++];
                res[restR--] = chas[R--];
            } else if (dp[L + 1][R] < dp[L][R - 1]) {
                res[restL++] = chas[L];
                res[restR--] = chas[L++];
            } else {
                res[restL++] = chas[R];
                res[restR--] = chas[R--];
            }
        }
        return String.valueOf(res);
    }

    public static int minAddCharacter(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int N = str.length();
        return process(str.toCharArray(), 0, N - 1);
    }

    public static void main(String[] args) {
        String str = "abc12ba";
        System.out.println(dpWays(str));
        System.out.println(getPalindrome(str));
    }
}
