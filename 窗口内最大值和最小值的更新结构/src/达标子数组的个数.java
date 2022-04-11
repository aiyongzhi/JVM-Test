import java.util.Arrays;
import java.util.LinkedList;

public class 达标子数组的个数 {
    /*
     * 题目：给定一个数组arr,和一个数num
     * sub表示arr的某一个子数组，如果sub.max-sub.min<=num条件成立.
     * 我们就认为这个子数组是达标的，请返回所有达标子数组的个数
     * */

    /*
     * 子数组问题有时可以用窗口来解题
     * 用窗口来解题的时候一定要要求L指针和R指针不回退，才能做到最优解
     *
     * 这题两个隐藏关键信息
     * R++ 向窗口中加入一个元素时，一定会让窗口中最大值与最小值的差值变大或者不变
     * L++ 删除窗口中的一个元素时，                            变小或者不变
     * */

    //用暴力方法当对数器
    public static int comparatorWays(int[] arr, int num) {
        if (arr == null || arr.length == 0 || num < 0) {
            return 0;
        }
        //暴力枚举所有的子数组，在遍历所有的子数组验证是否达标
        int count = 0;

        for (int L = 0; L < arr.length; L++) {
            for (int R = L; R < arr.length; R++) {
                int max = arr[L];
                int min = arr[L];
                for (int i = L + 1; i <= R; i++) {
                    max = Math.max(max, arr[i]);
                    min = Math.min(min, arr[i]);
                }
                if (max - min <= num) {
                    count++;
                }
            }
        }
        return count;
    }

    public static int getAllOkArrayCount(int[] arr, int num) {
        if (arr == null || arr.length == 0 || num < 0) {
            return 0;
        }
        //利用窗口内最大值和最小值的更新结构来解题
        //利用双端队列来实现窗口
        //用两个双端队列，一个维护窗口中的最大值结构,一个维护窗口中的最小值结构
        LinkedList<Integer> minQueue = new LinkedList<>();
        LinkedList<Integer> maxQueue = new LinkedList<>();
        int count = 0;//计数
        int R = 0;
        for (int L = 0; L < arr.length; L++) {
            //R往右扩到不能再往右的地方
            while (R < arr.length) {
                //将R位置的元素加入窗口中
                while (!minQueue.isEmpty() && (arr[minQueue.peekLast()] >= arr[R])) {
                    minQueue.pollLast();
                }
                minQueue.addLast(R);

                while (!maxQueue.isEmpty() && (arr[maxQueue.peekLast()] <= arr[R])) {
                    maxQueue.pollLast();
                }
                maxQueue.addLast(R);

                if (arr[maxQueue.peekFirst()] - arr[minQueue.peekFirst()] > num) {
                    break;
                }
                R++;
            }
            //收集以L为子数组左边界的所有可能答案
            count += (R - L);
            if (!minQueue.isEmpty() && L == minQueue.peekFirst()) {
                minQueue.pollFirst();
            }
            if (!maxQueue.isEmpty() && L == maxQueue.peekFirst()) {
                maxQueue.pollFirst();
            }
        }
        return count;
    }

    //写一个对数器来测试
    public static int[] getGenerateRandomArray(int maxValue, int maxLen) {
        int len = (int) (Math.random() * maxLen) + 1;
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTime = 100000;
        int maxValue = 100;
        int maxLen = 20;
        int maxDistance = 15;

        boolean success = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = getGenerateRandomArray(maxValue, maxLen);
            int num = (int) (Math.random() * maxDistance) + 1;

            int ans1 = getAllOkArrayCount(arr, num);
            int ans2 = comparatorWays(arr, num);
            if (ans1 != ans2) {
                System.out.println(Arrays.toString(arr));
                System.out.println("num==" + num);
                System.out.println("ans1==" + ans1);
                System.out.println("an2==" + ans2);
                success = false;
                break;
            }
        }
        System.out.println(success ? "Ok!" : "Fuck!");
    }
}
