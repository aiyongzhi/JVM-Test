public class 让司机整体获得的收入最大 {
    //题目描述：正值下班高峰时期，现有可载客司机2*N人，调度中心将调度相关司机服务A，B两个出行高峰地区
    //第i个司机前往A地区可以获得的收入为income[i][0]
    //第i个司机前往B地区可以获得的收入为income[i][1]
    //请求出最终使所有司机获得收入总和最大,但必须保证A地区和B地区都有N位司机在服务

    //尝试的经验总结：1：先判断是那种尝试模型
    //2:在每种尝试模型下，找准一个区分点，进行细致的分类讨论

    //先写暴力递归
    //[0....i-1]号司机已经决定好了，现在只需求出[i....]号司机去A或B地所能获得的最大收入
    //i:现在尝试到了第几位司机
    //A:表示A地区的人数
    //B:表示B地区的人数
    //写暴力递归时一定要注意：可变参数一定要尽可能的少，
    //可变参数越少，改成动态规划时，时间复杂度越低
    public static int process(int[][] incomes, int i, int A, int B) {
        if (i == incomes.length) {
            return 0;
        }
        int p1 = 0;
        if (A < ((incomes.length) >> 1)) {
            p1 += (incomes[i][0]) + process(incomes, i + 1, A + 1, B);
        }
        int p2 = 0;
        if (B < ((incomes.length) / 2)) {
            p2 += (incomes[i][1] + process(incomes, i + 1, A, B + 1));
        }
        return Math.max(p1, p2);
    }

    //我们可以精巧的设计参数，使三个可变参数的问题转变为两个可变参数
    //其它参数和上一种解法都一样，但这里有一个不一样的地方
    //这里的rest是A区域还剩余的名额
    public static int process1(int[][] incomes, int index, int rest) {
        //base case
        if (incomes.length == index) {
            return 0;
        }
        //还有司机:

        //情况1：剩余的司机人数等于A区域还剩余的名额
        //A区域还有名额，B区域满了
        if (incomes.length - index == rest) {
            return incomes[index][0] + process1(incomes, index + 1, rest - 1);
        }

        //B区域满了，A区域还有名额
        if (rest == 0) {
            return incomes[index][1] + process1(incomes, index + 1, rest);
        }
        //A区域和B区域可以自由安排
        int p1 = incomes[index][0] + process1(incomes, index + 1, rest - 1);
        int p2 = incomes[index][1] + process1(incomes, index + 1, rest);
        return Math.max(p1, p2);
    }

    public static int maxIncomes(int[][] incomes) {
        if (incomes == null || incomes.length == 0 || (incomes.length & 1) != 0) {
            return 0;
        }
        int N = incomes.length / 2;
        return process1(incomes, 0, N);
    }

    //改成动态规划
    public static int dpWays(int[][] incomes) {
        if (incomes == null || incomes.length == 0 || (incomes.length & 1) != 0) {
            return 0;
        }
        int N = incomes.length;
        int[][] dp = new int[2][(N / 2) + 1];
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest < dp[0].length; rest++) {
                if (incomes.length - index == rest) {//A区域还有名额，B区域没有名额
                    dp[index % 2][rest] = incomes[index][0] + dp[(index + 1) % 2][rest - 1];
                } else if (rest == 0) {//A区域没有名额，B区域还有名额
                    dp[index % 2][rest] = incomes[index][1] + dp[(index + 1) % 2][rest];
                } else {//A区域和B区域都有名额
                    int p1 = incomes[index][0] + dp[(index + 1) % 2][rest - 1];
                    int p2 = incomes[index][1] + dp[(index + 1) % 2][rest];
                    dp[index % 2][rest] = Math.max(p1, p2);
                }
            }
        }
        return dp[0][N / 2];
    }

    public static void main(String[] args) {
        int[][] incomes = {{6, 3}, {3, 6}, {7, 2}, {4, 9}, {5, 5}, {2, 9}};
        int N = incomes.length;
        System.out.println(maxIncomes(incomes));
        System.out.println(process(incomes, 0, 0, 0));
        System.out.println(dpWays(incomes));
    }
}
