import java.util.Arrays;
import java.util.Stack;

public class 子数组最小和 {
    //给定一个数组arr,请返回arr中所有子数组中最小值的和
    //子数组的最小和问题

    //暴力解
    public static int comparatorWay(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int minSum = 0;
        for (int L = 0; L < arr.length; L++) {
            for (int R = L; R < arr.length; R++) {
                int min = arr[L];
                for (int i = L + 1; i <= R; i++) {
                    min = Math.min(min, arr[i]);
                }
                minSum += min;
            }
        }
        return minSum;
    }

    //单调栈的解法
    public static int sumSubArrayMin(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            //添加元素
            while (!stack.isEmpty() && (arr[stack.peek()] >= arr[i])) {
                int popIndex = stack.pop();
                int L = stack.isEmpty() ? -1 : stack.peek();
                int R = i;
                sum += ((popIndex - L) * (R - popIndex)) * arr[popIndex];
            }
            stack.add(i);
        }
        //清空栈
        while (!stack.isEmpty()) {
            int popIndex = stack.pop();
            int L = stack.isEmpty() ? -1 : stack.peek();
            int R = arr.length;
            sum += ((popIndex - L) * (R - popIndex)) * arr[popIndex];
        }
        return sum;
    }

    //测试用例
    public static int[] getGenerateRandomArray(int maxValue, int maxLen) {
        int len = (int) (Math.random() * maxLen) + 1;
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTime = 1000000;
        int maxValue = 10;
        int maxLen = 20;
        for (int i = 0; i < testTime; i++) {
            int[] arr = getGenerateRandomArray(maxValue, maxLen);
            int ans1 = comparatorWay(arr);
            int ans2 = sumSubArrayMin(arr);
            if (ans1 != ans2) {
                System.out.println(Arrays.toString(arr));
                System.out.println("暴力解:" + ans1);
                System.out.println("单调栈:" + ans2);
                break;
            }
        }
        System.out.println("Ops!");
    }
    //在数据量很大的时候要通过取模来让数据变小，但此时容易出现精确丢失问题
    //单调栈问题都可以通过用数组来实现栈从而优化
}
