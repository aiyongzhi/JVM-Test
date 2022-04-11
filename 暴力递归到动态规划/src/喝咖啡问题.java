import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class 喝咖啡问题 {
    public static class Machine {
        public int cookTime;
        public int nextOK;

        public Machine(int a, int b) {
            cookTime = a;
            nextOK = b;
        }
    }

    public static class MyComparator implements Comparator<Machine> {
        public int compare(Machine o1, Machine o2) {
            return o1.nextOK - o2.nextOK;
        }
    }

    //把原问题拆分成两个问题
    //1)如何生成N个人喝完咖啡的时间点
    public static int[] drinkTimes(int[] arr, int N) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[] ans = new int[N];
        int curTime = 0;
        PriorityQueue<Machine> minHeap = new PriorityQueue<>(new MyComparator());
        //1:初始化堆
        for (int i = 0; i < arr.length; i++) {
            minHeap.add(new Machine(arr[i], arr[i]));
        }
        //每一次从堆中弹出一杯咖啡，让队头的人喝，并生成此咖啡机下一次煮好咖啡的时间点
        for (int i = 0; i < N; i++) {
            Machine cur = minHeap.poll();
            curTime = cur.nextOK;
            ans[i] = curTime;
            cur.nextOK = curTime + cur.cookTime;
            minHeap.add(cur);
        }
        return ans;
    }

    //machine[i]表示第i台机器煮一杯咖啡需要的时间
    //a是用洗咖啡杯机器让咖啡杯变干净需要的时间
    //b是让咖啡杯自然挥发干净需要的时间
    //请返回所有人喝完咖啡并使咖啡杯变干净的最早时间
    //暴力递归
    public static int minTime1(int[] machine, int N, int a, int b) {
        if (machine == null || machine.length == 0 || N <= 0 || a <= 0 || b <= 0) {
            return 0;
        }
        int[] drinks = drinkTimes(machine, N);
        return process1(drinks, a, b, 0, 0);
    }

    //暴力递归来写
    //当前drinks[0...i-1]的人都已经决策好了，不需要你处理
    //你只需要处理drinks[i...]咖啡杯。返回所有咖啡杯变干净的最早时刻
    //存在业务限制的模型
    //limit:表示洗咖啡杯机器可用的时间点
    public static int process1(int[] drinks, int a, int b, int i, int limit) {
        if (i == drinks.length) {
            return 0;
        }
        //有人
        //让咖啡杯自然挥发
        int p1 = drinks[i] + b;
        //让洗咖啡杯机器来洗
        int pick1 = Math.max(p1, process1(drinks, a, b, i + 1, limit));
        int p2 = Math.max(drinks[i], limit) + a;
        int pick2 = Math.max(p2, process1(drinks, a, b, i + 1, p2));
        return Math.min(pick1, pick2);
    }

    //动态规划
    public static int minTime2(int[] machine, int N, int a, int b) {
        if (machine == null || machine.length == 0 || N <= 0 || a <= 0 || b <= 0) {
            return 0;
        }
        int[] drinks = drinkTimes(machine, N);
        //求出limit的最大值
        int maxLimit = 0;
        for (int i = 0; i < N; i++) {
            maxLimit = Math.max(maxLimit, drinks[i]) + a;
        }
        int[][] dp = new int[N + 1][maxLimit + 1];
        for (int i = N - 1; i >= 0; i--) {
            for (int limit = 0; limit <= maxLimit; limit++) {
                int p1 = drinks[i] + b;
                //让洗咖啡杯机器来洗
                int pick1 = Math.max(p1, dp[i + 1][limit]);
                int p2 = Math.max(drinks[i], limit) + a;
                int pick2 = Integer.MAX_VALUE;
                if (p2 <= maxLimit) {
                    pick2 = Math.max(p2, dp[i + 1][p2]);
                }
                dp[i][limit] = Math.min(pick1, pick2);
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[] machine = {1, 3, 7, 10};
        int N = 8;
        int a = 2;
        int b = 5;
        System.out.println(minTime1(machine, N, a, b));
        System.out.println(minTime2(machine, N, a, b));
    }
}
