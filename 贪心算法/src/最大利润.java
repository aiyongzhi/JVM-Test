import java.util.*;

public class 最大利润 {
    //正数数组costs,正数数组profits,正数K，正数M
    //costs[i]表示i号项目的花费
    //profits[i]:表示i号项目的利润
    //K表示你最多只能串行的做i个项目
    //M表示你的初始资金
    //说明：你每做完一个项目，马上获得收益，可以支持你去做下一个项目，不能并行的做项目
    //输出：你最后获得的最大钱数
    public static class Program {
        public int cost;
        public int profit;

        public Program(int a, int b) {
            cost = a;
            profit = b;
        }
    }

    //在当前资金能解锁的项目中，做利润最大的，有了更多资金后再解锁更多的任务
    public static class costComparator implements Comparator<Program> {
        public int compare(Program o1, Program o2) {
            return o1.cost - o2.cost;
        }
    }

    public static class profitComparator implements Comparator<Program> {
        public int compare(Program o1, Program o2) {
            return o2.profit - o1.profit;
        }
    }

    public static int maxProfit1(int[] costs, int[] profits, int w, int k) {
        if (costs.length == 0 || profits.length == 0 || costs.length != profits.length || k == 0) {
            return 0;
        }
        PriorityQueue<Program> costHeap = new PriorityQueue<>(new costComparator());
        PriorityQueue<Program> canDo = new PriorityQueue<>(new profitComparator());
        for (int i = 0; i < costs.length; i++) {
            costHeap.add(new Program(costs[i], profits[i]));
        }
        while (k-- != 0) {
            //解锁项目
            while (!costHeap.isEmpty() && costHeap.peek().cost <= w) {
                canDo.add(costHeap.poll());
            }
            //在解锁的任务区中做一个利润最高的项目
            if (canDo.isEmpty()) {
                break;
            }
            w += canDo.poll().profit;
        }
        return w;
    }

    //来个暴力方法
    public static int maxProfits2(int[] costs, int[] profits, int w, int k) {
        if (costs.length == 0 || profits.length == 0 || costs.length != profits.length || k == 0) {
            return 0;
        }
        ArrayList<Program> programs = new ArrayList<>();
        for (int i = 0; i < costs.length; i++) {
            programs.add(new Program(costs[i], profits[i]));
        }
        return process(programs, k, w);
    }

    //递归含义：所有还没有做过的项目全在programs中,其中的项目只要资金够你可以任意选择
    //请返回能获得的最大利润
    //rest:还剩于项目数
    //w做这个项目前所积累到的资金
    public static int process(ArrayList<Program> programs, int rest, int w) {
        //base case
        if (programs.size() == 0 || rest == 0) {
            return w;
        }
        //还有项目可以做
        int maxProfits = w;
        for (int i = 0; i < programs.size(); i++) {
            if (programs.get(i).cost <= w) {
                ArrayList<Program> next = new ArrayList<>(programs);
                next.remove(i);
                maxProfits = Math.max(maxProfits, process(next, rest - 1, w +
                        programs.get(i).profit));
            }
        }
        return maxProfits;
    }

    public static int[] generateRandomArray(int maxLen, int maxValue) {
        int len = (int) (Math.random() * maxLen) + 1;
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }

    public static int[] copyArray(int[] m) {
        int[] res = new int[m.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = m[i];
        }
        return res;
    }

    public static void main(String[] args) {
        int testTime = 10000;
        int maxLen = 6;
        int maxValue = 100;
        int firstMoney = 10;
        for (int i = 0; i < testTime; i++) {
            int w = (int) (Math.random() * firstMoney) + 1;
            int k = (int) (Math.random() * (maxLen - 1)) + 1;
            int[] costs = generateRandomArray(maxLen, maxValue);
            int[] profits = generateRandomArray(maxLen, maxValue);
            int ans1 = maxProfit1(costs, profits, w, k);
            int ans2 = maxProfits2(costs, profits, w, k);
            if (ans1 != ans2) {
                System.out.println("Fuck!! fuck");
                System.out.println(Arrays.toString(costs));
                System.out.println(Arrays.toString(profits));
                System.out.println("w==" + w);
                System.out.println("k==" + k);
                System.out.println("ans1==" + ans1);
                System.out.println("ans2==" + ans2);
                break;
            }
        }
        System.out.println("finish!!!");
    }
}
