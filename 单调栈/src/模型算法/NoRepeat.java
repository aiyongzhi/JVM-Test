package 模型算法;

import java.util.Stack;

//单调栈结构 栈中的数据由栈底到栈顶是递增结构
//一般用于求解数组中 一个位置左边离他最近且比它小的数和这个位置右边离它最近且比它小的数
//无重复值版本
//入栈规则:入栈时，将比当前数值大的元素全弹出，弹出的时候统计信息
//再把当前元素压入栈中
public class NoRepeat {
    //返回二维数组的含义
    //res[i][0]:下标为i的数左边比它小且离他最近的元素是下标为res[i][0]的元素
    //res[i][1]          右边                         res[i][1]的元素
    //如果没有则值为-1
    public static int[][] getNearNoRepeat(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[][] res = new int[arr.length][2];
        Stack<Integer> stack = new Stack<>();//栈中存放的是下标
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && (arr[stack.peek()] > arr[i])) {
                int popIndex = stack.pop();
                int leftLessNear = stack.isEmpty() ? -1 : stack.peek();
                res[popIndex][0] = leftLessNear;
                res[popIndex][1] = i;
            }
            stack.add(i);
        }
        //清空栈中的元素
        while (!stack.isEmpty()) {
            int popIndex = stack.pop();
            int leftLessNear = stack.isEmpty() ? -1 : stack.peek();
            res[popIndex][0] = leftLessNear;
            res[popIndex][1] = -1;
        }
        return res;
    }
}
