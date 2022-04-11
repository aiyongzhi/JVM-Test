import java.util.Stack;

public class 直方图的最大矩形面积 {
    //力扣链接:https://leetcode-cn.com/problems/0ynMMM/
    //给你一个非负整数数组heights,表示直方图中每一个小矩形的高度,每一个小矩形的宽度默认是1
    //请返回这个直方图能勾勒出最大的矩形面积是多少

    //经过分析可以把题目抽象化为
    //给定一个非负整数数组arr,其中每个子数组sub,都有一个指标A=sub.min*sub.length
    //请返回arr中全部子数组中的指标的最大值

    //暴力方法时间复杂度为O(N^3)
    public static int comparatorWay(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int max = 0;
        for (int L = 0; L < heights.length; L++) {
            for (int R = L; R < heights.length; R++) {
                int min = heights[L];
                for (int i = L + 1; i <= R; i++) {
                    min = Math.min(min, heights[i]);
                }
                max = Math.max(max, min * (R - L + 1));
            }
        }
        return max;
    }

    //单调栈模型一般可以用于求一个子数组中最小值以及与子数组中最小值有关的问题
    //可以将时间复杂度优化至O(N)
    public static int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int max = 0;
        //当一个数子从单调栈中弹出来的时候做统计
        Stack<Integer> stack = new Stack<>();//单调栈中存储的是元素的下标
        for (int i = 0; i < heights.length; i++) {
            //进栈 有相同数据时也可以弹出
            while (!stack.isEmpty() && (heights[stack.peek()] >= heights[i])) {
                int popIndex = stack.pop();
                max = Math.max(max, (stack.isEmpty() ? i : (i - 1 - stack.peek())) * heights[popIndex]);
            }
            stack.add(i);
        }
        //清空栈做最后的统计
        while (!stack.isEmpty()) {
            int popIndex = stack.pop();
            max = Math.max(max, (stack.isEmpty() ? heights.length : (heights.length - 1 - stack.peek())) * heights[popIndex]);
        }
        return max;
    }

    //对数器
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
            int[] arr = getGenerateRandomArray(maxValue, maxLen);
            int ans1 = comparatorWay(arr);
            int ans2 = largestRectangleArea(arr);
            if (ans1 != ans2) {
                System.out.println("Fuck!");
                break;
            }
        }
        System.out.println("Ops!");

    }
}
