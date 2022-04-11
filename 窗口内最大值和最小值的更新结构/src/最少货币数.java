import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class 最少货币数 {
    /*
     * 给定一个正数数组arr表示一个货币数组。再给定一个正数aim
     * arr[i]:表示一张面值为arr[i]的货币
     * 返回组成aim所需的最小货币张数
     * 因为是求最少张数，因此认为值相同的货币是相同或者不相同均不重要了
     * */

    //暴力递归1不压缩张数的暴力递归
    public static int process1(int i, int[] arr, int rest) {
        //在递归上游处理保证rest>=0
        //base case
        if (i == arr.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }
        //还有货币可以自由选择
        //不要当前货币
        int p1 = process1(i + 1, arr, rest);
        //要当前货币
        int p2 = Integer.MAX_VALUE;
        if (rest >= arr[i]) {
            int next = process1(i + 1, arr, rest - arr[i]);
            if (next != Integer.MAX_VALUE) {
                p2 = 1 + next;
            }
        }
        return Math.min(p1, p2);
    }

    //只需要改成记忆化搜索即可
    public static int memoryDFS(int i, int[] arr, int rest, HashMap<String, Integer> dp) {
        String cur = i + "_" + rest;
        if (dp.containsKey(cur)) {
            return dp.get(cur);
        }
        if (i == arr.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }
        //还有货币可以自由选择
        //不要当前货币
        int p1 = memoryDFS(i + 1, arr, rest, dp);
        //要当前货币
        int p2 = Integer.MAX_VALUE;
        if (rest >= arr[i]) {
            int next = memoryDFS(i + 1, arr, rest - arr[i], dp);
            if (next != Integer.MAX_VALUE) {
                p2 = 1 + next;
            }
        }
        dp.put(cur, Math.min(p1, p2));
        return dp.get(cur);
    }

    //压缩张数的暴力递归
    public static int[][] getMoneyAndNumbersArray(int[] money) {
        if (money == null || money.length == 0) {
            return null;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int n : money) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        int[] coins = new int[map.size()];
        int[] zhang = new int[map.size()];
        int index = 0;
        for (Integer key : map.keySet()) {
            coins[index] = key;
            zhang[index++] = map.get(key);
        }
        return new int[][]{coins, zhang};
    }

    /*
     * 递归含义[0...index-1]已经决策好，现在[index....]所有面值都可以任意选择张数
     * 请返回拼凑出rest所需要的最小张数
     *
     * 我们通过返回整型最大值来表示无论如果都完成不了
     * */
    public static int process2(int index, int[] c, int[] z, int rest) {
        //rest>=0
        //base case
        if (index == c.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }
        //当前货币可以任意选择张数
        int ans = Integer.MAX_VALUE;
        for (int zhang = 0; zhang <= z[index] && zhang * c[index] <= rest; zhang++) {
            int next = process2(index + 1, c, z, rest - zhang * c[index]);
            if (next != Integer.MAX_VALUE) {
                ans = Math.min(ans, next + zhang);
            }
        }
        return ans;
    }

    public static int minZhang1(int[] money, int aim) {
        if (money == null || money.length == 0 || aim < 0) {
            return Integer.MAX_VALUE;
        }
        int[][] date = getMoneyAndNumbersArray(money);
        int[] coins = date[0];
        int[] zhang = date[1];
        return process2(0, coins, zhang, aim);
    }

    //把process2改成动态规划
    public static int dpWays1(int[] money, int aim) {
        if (money == null || money.length == 0 || aim < 0) {
            return Integer.MAX_VALUE;
        }
        int[][] date = getMoneyAndNumbersArray(money);
        int[] c = date[0];
        int[] z = date[1];

        int N = c.length;
        int[][] dp = new int[N + 1][aim + 1];
        for (int rest = 1; rest <= aim; rest++) {
            dp[N][rest] = Integer.MAX_VALUE;
        }
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ans = Integer.MAX_VALUE;
                for (int zhang = 0; zhang <= z[index] && zhang * c[index] <= rest; zhang++) {
                    int next = dp[index + 1][rest - zhang * c[index]];
                    if (next != Integer.MAX_VALUE) {
                        ans = Math.min(ans, next + zhang);
                    }
                }
                dp[index][rest] = ans;
            }
        }
        return dp[0][aim];
    }

    //利用窗口为基础的动规斜优
    //对枚举行为进行了优化
    //若货币有M种，其时间复杂度为O(M*aim)
    public static int getCurCostZhang(int pre, int cur, int coin) {
        return (cur - pre) / coin;
    }

    public static int dpWays2(int[] money, int aim) {
        if (money == null || money.length == 0 || aim < 0) {
            return Integer.MAX_VALUE;
        }
        int[][] date = getMoneyAndNumbersArray(money);
        int[] c = date[0];
        int[] z = date[1];

        int N = c.length;
        int[][] dp = new int[N + 1][aim + 1];
        for (int rest = 1; rest <= aim; rest++) {
            dp[N][rest] = Integer.MAX_VALUE;
        }
        //很难
        for (int index = N - 1; index >= 0; index--) {
            for (int mod = 0; mod < Math.min(c[index], aim + 1); mod++) {
                //用双端队列模拟窗口
                LinkedList<Integer> minQueue = new LinkedList<>();
                //起始列的答案可以直接收集
                minQueue.add(mod);
                dp[index][mod] = dp[index + 1][mod];
                for (int rest = mod + c[index]; rest <= aim; rest += c[index]) {
                    //向双端队列中加入元素
                    while (!minQueue.isEmpty() && (
                            dp[index + 1][minQueue.peekLast()] == Integer.MAX_VALUE ||
                                    dp[index + 1][minQueue.peekLast()] + getCurCostZhang(minQueue.peekLast(), rest, c[index])
                                            >= dp[index + 1][rest]
                    )) {
                        minQueue.pollLast();
                    }
                    minQueue.addLast(rest);
                    //把过期元素删除
                    int overdue = rest - (z[index] + 1) * c[index];
                    if (!minQueue.isEmpty() && overdue == minQueue.peekFirst()) {
                        minQueue.pollFirst();
                    }
                    //收集答案
                    dp[index][rest] = dp[index + 1][minQueue.peekFirst()] + getCurCostZhang(minQueue.peekFirst(), rest, c[index]);
                }
            }
        }
        return dp[0][aim];
    }

    //写一个对数器
    public static int[] getGenerateRandomArray(int maxValue, int maxLen) {
        int len = (int) (Math.random() * maxLen) + 1;
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTime = 10000;
        int maxLen = 100;
        int maxValue = 20;

        int maxAim = 500;
        for (int i = 0; i < testTime; i++) {
            int[] money = getGenerateRandomArray(maxValue, maxLen);
            int aim = (int) (Math.random() * maxAim) + 1;
            int ans1 = memoryDFS(0, money, aim, new HashMap<>());
            int ans2 = dpWays1(money, aim);
            int ans3 = dpWays2(money, aim);
            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println("Fuck!");
                break;
            }
        }
        System.out.println("Ops!");
    }
}
