public class 不同路径 {
    //力扣第12题：不同路径
    //力扣链接：https://leetcode-cn.com/problems/unique-paths/
    //暴力尝试：在每一个位置都可以尝试向下或者向右走
    //一个m*n的矩阵
    //现在机器人位于i，j位置
    //加一个记忆化搜索就ok
    public int process(int m, int n, int i, int j, int[][] dp) {
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        //base case
        if (i == m - 1 && j == n - 1) {
            return 1;
        }
        int next1 = 0;//向下走的总方法数
        int next2 = 0;//向右走的总方法数
        if (j + 1 < n) {
            next1 = process(m, n, i, j + 1, dp);
        }
        if (i + 1 < m) {
            next2 = process(m, n, i + 1, j, dp);
        }
        dp[i][j] = next1 + next2;
        return dp[i][j];
    }

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        return process(m, n, 0, 0, dp);
    }
}
