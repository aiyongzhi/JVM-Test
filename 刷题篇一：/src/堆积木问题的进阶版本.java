import java.util.Arrays;
import java.util.PriorityQueue;

public class 堆积木问题的进阶版本 {
    //给你一个数值arr，arr[i]表示第i块积木的质量
    //现在有如下规则，给你N快积木，摆积木时必须遵循以下两个原则，
    //1):你想把一块积木摆在另一块积木上面，必须保证上面的积木质量不小于下面积木的质量
    //2):同时你还得保证，上面积木与下面积木的质量差不超过x
    //3)你拥有k块魔方积木，魔方积木可以变换出任意质量的积木
    //现在给定一个长度为N的数值arr和x，请你给出至少要摆多少堆
    public static int allHeap(int[] arr, int x, int k) {
        if (arr == null || arr.length == 0 || x < 0) {
            return 0;
        }
        Arrays.sort(arr);
        int res = 0;
        int i = 0;//每一堆的开始
        int j = 0;
        PriorityQueue<Integer> minQueue = new PriorityQueue<>();
        //有魔方积木后，我们看看相邻的堆合并的代价，优先合并代价最小的堆
        while (i < arr.length) {
            while (j + 1 < arr.length && (arr[j + 1] - arr[j] <= x)) {
                j++;
            }
            res++;
            if (j + 1 < arr.length) {
                minQueue.add(arr[j + 1] - arr[j]);
            }
            i = j + 1;
            j = i;
        }
        while (!minQueue.isEmpty()) {
            int distance = minQueue.poll();
            if (k >= (distance / x)) {
                res--;
                k -= (distance / x);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {7, 4, 1, 9, 13};
        int x = 3;
        int k = 2;
        System.out.println(allHeap(arr, x, k));
    }
}
