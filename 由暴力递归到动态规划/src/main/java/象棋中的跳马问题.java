public class 象棋中的跳马问题 {
    //我们定义在10行9列的棋盘中 马开始位于0行0列
    //请问马跳K步跳到到a行b列的方法数

    //暴力递归，递归含义：最终跳到a行b列   现在马在i行 j列，还剩rest步 返回方法数
    public static int process(int a, int b, int rest, int i, int j) {
        if (i < 0 || i > 9 || j < 0 || j > 8) {
            //我们认为越界的方法种数为0
            return 0;
        }
        if (rest == 0) {
            return i == a && j == b ? 1 : 0;
        }
        //rest>0 还有步数可以跳
        return process(a, b, rest - 1, i - 2, j + 1) +
                process(a, b, rest - 1, i - 1, j + 2) +
                process(a, b, rest - 1, i + 1, j + 2) +
                process(a, b, rest - 1, i + 2, j + 1) +
                process(a, b, rest - 1, i + 2, j - 1) +
                process(a, b, rest - 1, i + 1, j - 2) +
                process(a, b, rest - 1, i - 2, j - 1) +
                process(a, b, rest - 1, i - 1, j - 2);
    }

    public static int getAllWays(int a, int b, int rest) {
        return process(a, b, rest, 0, 0);
    }

    //改成因为每一层都依赖于下一层，我们可以一层一层向上推：把它降成二维动态规划问题
    public static int dpWays(int a, int b, int rest) {
        int[][][] dp = new int[10][9][rest + 1];
        //初始化第一层
        dp[a][b][0] = 1;//三维动态规划表
        for (int k = 1; k <= rest; k++) {//每一层
            for (int i = 0; i < 10; i++) {//每一行
                for (int j = 0; j < 9; j++) {//每一列
                    if (i - 2 >= 0 && j + 1 <= 8) {
                        dp[i][j][k] += dp[i - 2][j + 1][k - 1];
                    }
                    if (i - 1 >= 0 && j + 2 <= 8) {
                        dp[i][j][k] += dp[i - 1][j + 2][k - 1];
                    }
                    if (i + 1 <= 9 && j + 2 <= 8) {
                        dp[i][j][k] += dp[i + 1][j + 2][k - 1];
                    }
                    if (i + 2 <= 9 && j + 1 <= 8) {
                        dp[i][j][k] += dp[i + 2][j + 1][k - 1];
                    }
                    if (i + 2 <= 9 && j - 1 >= 0) {
                        dp[i][j][k] += dp[i + 2][j - 1][k - 1];
                    }
                    if (i + 1 <= 9 && j - 2 >= 0) {
                        dp[i][j][k] += dp[i + 1][j - 2][k - 1];
                    }
                    if (i - 2 >= 0 && j - 1 >= 0) {
                        dp[i][j][k] += dp[i - 2][j - 1][k - 1];
                    }
                    if (i - 1 >= 0 && j - 2 >= 0) {
                        dp[i][j][k] += dp[i - 1][j - 2][k - 1];
                    }
                }
            }
        }
        return dp[0][0][rest];
    }

    public static void main(String[] args) {
        int a = 7;
        int b = 7;
        int k = 10;
        System.out.println(getAllWays(a, b, k));
        System.out.println(dpWays1(a, b, k));
    }

    public static int dpWays1(int a, int b, int rest) {
        int[][][] dp = new int[10][9][rest + 1];
        dp[a][b][0] = 1;
        for (int k = 1; k <= rest; k++) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 9; j++) {
                    dp[i][j][k % 2] =
                            (getValue(dp, i - 2, j + 1, (k - 1) % 2) +
                                    getValue(dp, i - 1, j + 2, (k - 1) % 2) +
                                    getValue(dp, i + 2, j + 1, (k - 1) % 2) +
                                    getValue(dp, i + 1, j + 2, (k - 1) % 2) +
                                    getValue(dp, i + 2, j - 1, (k - 1) % 2) +
                                    getValue(dp, i + 1, j - 2, (k - 1) % 2) +
                                    getValue(dp, i - 1, j - 2, (k - 1) % 2) +
                                    getValue(dp, i - 2, j - 1, (k - 1) % 2));
                }
            }
        }
        return dp[0][0][rest % 2];
    }

    public static int getValue(int[][][] dp, int i, int j, int rest) {
        if (i < 0 || i > 9 || j < 0 || j > 8) {
            //这种方法认为无效
            return 0;
        }
        return dp[i][j][rest];
    }

    public static int process1(int a, int b, int i, int j, int rest) {
        //base case
        if (i < 0 || i > 9 || j < 0 || j > 8) {
            //这种方法认为无效
            return 0;
        }
        //base case2:如果剩余步数为0
        if (rest == 0) {
            return i == a && j == b ? 1 : 0;
        }
        return process1(a, b, i - 2, j + 1, rest - 1) +
                process1(a, b, i - 1, j + 2, rest - 1) +
                process1(a, b, i + 2, j + 1, rest - 1) +
                process1(a, b, i + 1, j + 2, rest - 1) +
                process1(a, b, i + 2, j - 1, rest - 1) +
                process1(a, b, i + 1, j - 2, rest - 1) +
                process1(a, b, i - 1, j - 2, rest - 1) +
                process1(a, b, i - 2, j - 1, rest - 1);
    }
}
