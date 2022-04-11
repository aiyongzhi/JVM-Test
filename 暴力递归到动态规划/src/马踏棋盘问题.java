public class 马踏棋盘问题 {
    //现在有十行九列的棋盘，定义最坐下角为(0,0)位置
    //请问马从(0,0)位置出发，跳K步，到达(a,b)位置的方法数
    public static int getAllWays(int a, int b, int step) {
        if (a < 0 || a > 9 || b < 0 || b > 8 || step < 0) {
            return 0;
        }
        return process(0, 0, step, a, b);
    }

    //递归含义：现在在(i,j)位置，还有rest步可以走，最终要走到(a,b)位置，请返回方法数
    public static int process(int i, int j, int rest, int a, int b) {
        //base case
        if (i < 0 || i > 9 || j < 0 || j > 8) {
            return 0;
        }
        if (rest == 0) {
            return (i == a && j == b) ? 1 : 0;
        }
        //rest>0，可以正常跳
        return process(i - 2, j + 1, rest - 1, a, b) +
                process(i - 1, j + 2, rest - 1, a, b) +
                process(i + 1, j + 2, rest - 1, a, b) +
                process(i + 2, j + 1, rest - 1, a, b) +
                process(i + 2, j - 1, rest - 1, a, b) +
                process(i + 1, j - 2, rest - 1, a, b) +
                process(i - 2, j - 1, rest - 1, a, b) +
                process(i - 1, j - 2, rest - 1, a, b);
    }

    //动态规划
    public static int dpWays(int a, int b, int step) {
        if (a < 0 || a > 9 || b < 0 || b > 8 || step < 0) {
            return 0;
        }
        int[][][] dp = new int[10][9][step + 1];
        dp[a][b][0] = 1;
        for (int rest = 1; rest <= step; rest++) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 9; j++) {
                    dp[i][j][rest] = getEffectNum(dp, i - 2, j + 1, rest - 1) +
                            getEffectNum(dp, i - 1, j + 2, rest - 1) +
                            getEffectNum(dp, i + 1, j + 2, rest - 1) +
                            getEffectNum(dp, i + 2, j + 1, rest - 1) +
                            getEffectNum(dp, i + 2, j - 1, rest - 1) +
                            getEffectNum(dp, i + 1, j - 2, rest - 1) +
                            getEffectNum(dp, i - 2, j - 1, rest - 1) +
                            getEffectNum(dp, i - 1, j - 2, rest - 1);
                }
            }
        }
        return dp[0][0][step];
    }

    public static int getEffectNum(int[][][] dp, int i, int j, int rest) {
        if (i < 0 || i > 9 || j < 0 || j > 8) {
            return 0;
        }
        return dp[i][j][rest];
    }

    public static void main(String[] args) {
        int a = 7;
        int b = 7;
        int step = 10;
        System.out.println(getAllWays(a, b, step));
        System.out.println(dpWays(a, b, step));
    }
}
