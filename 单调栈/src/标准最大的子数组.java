import java.util.Stack;

public class 标准最大的子数组 {
    //给定一个正数数组arr,规定arr的每一个子数组sub都有一个指标
    //这个指标就是(sub.sum)*(sub.min)
    //请返回这个正数数组arr中的最大指标值是多少

    //暴力方法
    //时间复杂度为O(N)
    public static int comparatorWays(int[] arr) {
        if (arr == null || arr.length == 0) {
            return Integer.MIN_VALUE;//标记不存在
        }
        int max = Integer.MIN_VALUE;
        for (int L = 0; L < arr.length; L++) {
            for (int R = L; R < arr.length; R++) {
                int sum = arr[L];
                int min = arr[L];
                for (int i = L + 1; i <= R; i++) {
                    sum += arr[i];
                    min = Math.min(min, arr[i]);
                }
                max = Math.max(max, sum * min);
            }
        }
        return max;
    }

    //用单调栈优化
    //以数组中的每一个值为最小值
    //在单调栈中弹出一个元素的时候就能得知它左边比它小且最近的，右边比它小且最近的
    //于是我们就可以得到以弹出元素做为最小值的最大指标值
    public static int maxStandardSubArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return Integer.MIN_VALUE;
        }
        //因为我们要以O(1)的时间复杂度拿到sub和,所以需要预处理一个前缀和数组
        int[] sum = new int[arr.length];
        for (int i = 0; i < sum.length; i++) {
            sum[i] = (i - 1) >= 0 ? arr[i] + sum[i - 1] : arr[i];
        }
        //准备单调栈
        Stack<Integer> stack = new Stack<>();//栈中存放的是下标
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            //入栈 边界条件:相同值时候如何处理
            while (!stack.isEmpty() && (arr[stack.peek()] >= arr[i])) {
                int popIndex = stack.pop();
                //弹出元素的时候就处理
                max = Math.max(max, (stack.isEmpty() ? sum[i - 1] : (sum[i - 1] - sum[stack.peek()])) * arr[popIndex]);
            }
            stack.add(i);
        }
        //清空栈
        while (!stack.isEmpty()) {
            int popIndex = stack.pop();
            int R = arr.length - 1;
            max = Math.max(max, (stack.isEmpty() ? sum[R] : (sum[R] - sum[stack.peek()])) * arr[popIndex]);
        }
        return max;
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
        int maxLen = 100;
        for (int i = 0; i < testTime; i++) {
            int[] generateArray = getGenerateRandomArray(maxValue, maxLen);
            int ans1 = comparatorWays(generateArray);
            int ans2 = maxStandardSubArray(generateArray);
            if (ans1 != ans2) {
                System.out.println("Fuck!");
                break;
            }
        }
        System.out.println("Ops!");
    }
}
