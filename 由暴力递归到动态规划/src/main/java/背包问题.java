public class 背包问题 {
    //w[index]表示重量数组
    //v[index]表示价值数组
    //index 现在决策的货物
    //rest 当前背包剩余的承重力
    public static void main(String[] args) {
        int[] w = {2, 3, 7, 5};
        int[] v = {1, 3, 8, 6};
        System.out.println(process(w, v, 0, 10));
        System.out.println(dpWay(w, v, 10));
    }

    public static int process(int[] w, int[] v, int index, int rest) {
        //先写base case
        if (rest < 0) {
            return -1;//标记无效选择 说明当前货物不能要
        }
        if (index == w.length) {
            return 0;
        }
        //剩下的正常货物有价值
        int p1 = process(w, v, index + 1, rest);
        int p2 = -1;
        int p2Next = process(w, v, index + 1, rest - w[index]);
        if (p2Next != -1) {
            p2 = v[index] + p2Next;
        }
        return Math.max(p1, p2);
    }

    //由上述暴力递归改成动态规划
    //重要的是：把dp【】【】矩阵图的简略图画出来
    //最终需要的是dp图的 index==0 rest==bag的值
    public static int dpWay(int[] w, int[] v, int bag) {
        //
        int N = w.length;
        int[][] dp = new int[N + 1][bag + 1];//默认将第N行初始化为0
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= bag; rest++) {
                //目的填好 dp[index][bag]
                int p1 = dp[index + 1][rest];
                int p2 = -1;
                if (rest - w[index] >= 0) {
                    p2 = v[index] + dp[index + 1][rest - w[index]];
                }
                dp[index][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][bag];
    }
}
