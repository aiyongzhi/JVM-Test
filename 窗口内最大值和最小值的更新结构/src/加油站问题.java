import java.util.Arrays;
import java.util.LinkedList;

public class 加油站问题 {
    //现在有N个加油站 序号为0,1,2....N-1 给你一个长度为N的数组gas,再给你一个长度为N的数组cost
    //gas[i]表示i号加油站所能提供的燃料量，cost[i]表示从当前加油站到下一个加油站所消耗的燃料量
    //现在这个N个加油站是环形排列的,例如N=4时
    //0->1->2->3->0->1->2->3
    //假设汽车初始燃料量为0，油箱的容量无限大，请返回一个boolean类型的数组arr
    //arr[i]=true表示从i号加油站能走一圈回到出发点
    //arr[i]=false表示不能从i号加油站走一圈回到出发点

    public static boolean[] getArray(int[] gas, int[] cost) {
        if (gas == null || cost == null || gas.length != cost.length || gas.length == 0) {
            return null;
        }
        int[] profits = new int[gas.length];
        for (int i = 0; i < gas.length; i++) {
            profits[i] = gas[i] - cost[i];
        }
        //1:生成加工数组
        int[] arr = new int[2 * profits.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (i - 1 >= 0 ? arr[i - 1] : 0) + profits[i % profits.length];
        }
        //2:利用滑动窗口解题
        LinkedList<Integer> minQueue = new LinkedList<>();
        boolean[] result = new boolean[gas.length];
        int index = 0;
        for (int R = 0; R < arr.length - 1; R++) {
            //1:将R处的元素加入窗口
            while (!minQueue.isEmpty() && (arr[minQueue.peekLast()] >= arr[R])) {
                minQueue.pollLast();
            }
            minQueue.addLast(R);
            //2:L++
            if (!minQueue.isEmpty() && R - gas.length == minQueue.peekFirst()) {
                minQueue.pollFirst();
            }
            //3:收集答案
            if (R >= gas.length - 1) {
                int min = arr[minQueue.peekFirst()];
                result[index++] = (min - (R - gas.length >= 0 ? arr[R - gas.length] : 0) >= 0);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] gas = {1, 1, 5, 2, 6, 1};
        int[] cost = {3, 4, 1, 4, 0, 2};
        boolean[] result = getArray(gas, cost);
        System.out.println(Arrays.toString(result));
    }
}
