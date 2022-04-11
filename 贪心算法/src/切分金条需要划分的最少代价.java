import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class 切分金条需要划分的最少代价 {
    //现在给你一个长度为n的金条，请你将金条切分
    //给你一个数组arr,你最终要将金条切分成arr中的值
    //但你每次切分金条，都需要划分原金条长度的铜板作为代价
    //请你返回，划分最少代价的切割策略

    //利用哈夫曼编码的原理
    public static int minPrice1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : arr) {
            minHeap.add(num);
        }
        int res = 0;//返回的最小的代价
        while (minHeap.size() >= 2) {
            int sum = minHeap.poll() + minHeap.poll();
            res += sum;
            minHeap.add(sum);
        }
        return res;
    }

    public static int minPrice2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            arrayList.add(arr[i]);
        }
        return process(arrayList, 0);
    }

    //剩下还需要合并的金条防在集合中，已经花费的铜板为preCost
    //
    public static int process(ArrayList<Integer> arr, int preCost) {
        if (arr.size() == 1) {
            return preCost;
        }
        int res = Integer.MAX_VALUE;//第一次进来
        for (int i = 0; i < arr.size(); i++) {
            for (int j = i + 1; j < arr.size(); j++) {
                ArrayList<Integer> next = new ArrayList<>(arr);
                next.remove(i);
                next.add(arr.get(i) + arr.get(j));
                next.remove(j - 1);
                res = Math.min(res, process(next, preCost + arr.get(i) + arr.get(j)));
            }
        }
        return res;
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
        int maxLen = 5;
        int maxValue = 100;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxLen, maxValue);
            int[] arr2 = copyArray(arr1);
            int ans1 = minPrice1(arr1);
            int ans2 = minPrice2(arr2);
            if (ans1 != ans2) {
                System.out.println("Fuck! fuck!!");
                System.out.println(Arrays.toString(arr1));
                System.out.println(Arrays.toString(arr2));
                System.out.println("贪心算法的解: " + ans1);
                System.out.println("普通算法的解: " + ans2);
                break;
            }
        }
        System.out.println("finish!!");
    }
}
