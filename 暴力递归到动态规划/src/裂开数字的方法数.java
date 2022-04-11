import java.util.HashMap;

public class 裂开数字的方法数 {
    //给定一个正数，请返回将这个正数裂开的方法数，且每一个方法都是非递减的组合
    //例如正数1,裂开为[1]，方法数为1
    //例如正数2,裂开[1,1]和[2]方法数为2
    //例如正数3,裂开[1,1,1]和[1,2]和[3]三种方法

    //尝试
    //递归含义:现在还剩rest,上一次尝试的数字是pre
    //请尝试用1-rest的数字拼凑出rest,返回方法数
    public static int splitNum(int num) {
        if (num <= 0) {
            return 0;
        }
        return process(1, num);
    }

    //递归含义：pre代表上一个拆分出来的数字
    //rest:表示还剩余要拆的数字
    public static int process(int pre, int rest) {
        //base case
        if (rest == 0) {
            return 1;
        }
        //rest>0
        if (pre > rest) {
            return 0;
        }
        //枚举出拆当前数字的可能性
        int ans = 0;
        for (int n = pre; n <= rest; n++) {
            ans += process(n, rest - n);
        }
        return ans;
    }

    //改成动态规划
    public static int dpWays1(int num) {
        if (num <= 0) {
            return 0;//无效参数
        }
        int[][] dp = new int[num + 1][num + 1];
        //填第一列
        for (int pre = 1; pre <= num; pre++) {
            dp[pre][0] = 1;
        }
        //填普遍位置,由位置依赖关系可以得知，要从下向上，从左向右填dp表
        for (int pre = num; pre >= 1; pre--) {
            for (int rest = pre; rest <= num; rest++) {
                for (int n = pre; n <= rest; n++) {
                    dp[pre][rest] += dp[n][rest - n];
                }
            }
        }
        return dp[1][num];
    }

    //进行枚举行为的斜率优化
    //这个优化点很巧
    public static int dpWays2(int num) {
        if (num <= 0) {
            return 0;
        }
        int[][] dp = new int[num + 1][num + 1];
        //填特殊位置
        for (int i = 1; i <= num; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= num; i++) {
            dp[i][i] = 1;
        }
        for (int pre = num - 1; pre >= 0; pre--) {
            for (int rest = pre + 1; rest <= num; rest++) {
                dp[pre][rest] = dp[pre + 1][rest] + dp[pre][rest - pre];
            }
        }
        return dp[1][num];
    }

    public static void main(String[] args) {
        int testTime = 100;
        int maxValue = 15;
        for (int i = 0; i < testTime; i++) {
            int num = (int) (Math.random() * maxValue) + 1;
            int ans1 = splitNum(num);
            int ans2 = dpWays1(num);
            int ans3 = dpWays2(num);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("fuck!");
                break;
            }
        }
        System.out.println("finish!");
    }
}
