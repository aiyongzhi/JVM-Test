import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class 绳子最大覆盖点的数目 {
    //给你一个有序数组arr，arr[i]表示第i个点在x轴上的位置
    //给你一个正整数k，表示绳子的长度
    //请问：这个长度为k绳子最多能覆盖的点数
    public static int pointSum(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0) {
            return 0;
        }
        //准备一个队列，遍历到数组的每一个元素时，都以它为绳子的末尾点，把绳子左端覆盖不到的点全删除\
        Queue<Integer> queue = new LinkedList<>();
        int max = Integer.MIN_VALUE;
        for (int j : arr) {
            while (!queue.isEmpty() && (queue.peek() < j - k)) {
                queue.poll();
            }
            queue.add(j);
            max = Math.max(max, queue.size());
        }
        return max;
    }

    //暴力方法
    public static int pointSum1(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int j = i;
            while (j < arr.length && (arr[j] - arr[i]) <= k) {
                j++;
            }
            max = Math.max(max, j - i);
        }
        return max;
    }

    //法三：双指针：最优解 时间复杂度为O(N) 空间复杂度为O(1)
    public static int maxPointSum(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0) {
            return 0;
        }
        int L = 0;//表示绳子的开始位置
        int R = 0;//表示绳子的结尾位置
        //绳子尝试以每一个位置为开始位置尝试

        //L指针移动规则：当arr[R]-arr[L]>k时，L++
        //R指针移动规则:当arr[R]-arr[L]<=k,R++
        int max = 0;
        for (; L < arr.length; L++) {
            while (R < arr.length && (arr[R] - arr[L]) <= k) {
                R++;
            }
            max = Math.max(max, R - L);
        }
        return max;
    }

    public static void main(String[] args) {
        int testTime = 50000;
        int maxValue = 100;
        int maxKValue = 20;
        int maxSize = 100;
        boolean success = true;
        while (testTime-- != 0) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int k = randK(maxKValue);
            Arrays.sort(arr);
            if (pointSum(arr, k) != maxPointSum(arr, k)) {
                success = false;
                System.out.println(pointSum(arr, k));
                System.out.println(maxPointSum(arr, k));
                System.out.println(Arrays.toString(arr));
                System.out.println(k);
                break;
            }
        }
        System.out.println(success ? "OK!" : "Fake！fake");
    }

    public static int randK(int maxValue) {
        Random random = new Random();
        return random.nextInt(maxValue) + 1;
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        Random random = new Random();
        int[] arr = new int[random.nextInt(maxSize + 1)];
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            arr[i] = random.nextInt(maxValue + 1) - random.nextInt(maxValue + 1);
        }
        return arr;
    }
}
