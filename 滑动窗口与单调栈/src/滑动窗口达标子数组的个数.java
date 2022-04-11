import java.util.LinkedList;

public class 滑动窗口达标子数组的个数 {
    //题目：给你一个数组arr，和一个数字num
    //我们定义arr的子数组中max-min<=num的子数组为达标子数组
    //请返回一个arr中达标子数组的个数
    public static int allSubArray(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        LinkedList<Integer> minQueue = new LinkedList<>();
        LinkedList<Integer> maxQueue = new LinkedList<>();
        int L = 0;
        int R = 0;
        int res = 0;
        for (; L < arr.length; L++) {
            while (R < arr.length) {
                while (!maxQueue.isEmpty() && (arr[maxQueue.peekLast()] <= arr[R])) {
                    maxQueue.pollLast();
                }
                maxQueue.addLast(R);
                while (!minQueue.isEmpty() && (arr[minQueue.peekLast()] >= arr[R])) {
                    minQueue.pollLast();
                }
                minQueue.addLast(R);
                //查一下这个子数组是否达标
                if (arr[maxQueue.peekFirst()] - arr[minQueue.peekFirst()] > num) {
                    break;
                }
                R++;
            }
            res += R - L;
            //把arr[L]滑出窗口
            if (L == maxQueue.peekFirst()) {
                maxQueue.pollFirst();
            }
            if (L == minQueue.peekFirst()) {
                minQueue.pollFirst();
            }
        }
        return res;
    }
}
