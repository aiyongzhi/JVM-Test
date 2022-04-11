package 模型算法;

import java.util.*;

//允许数组中有重复值
public class canRepeat {
    public static int[][] getNearLess(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[][] res = new int[arr.length][2];
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && (arr[stack.peek().get(0)] > arr[i])) {
                List<Integer> popList = stack.pop();
                int leftLessNear = stack.isEmpty() ? -1 : (stack.peek().get(stack.peek().size() - 1));
                for (int popIndex : popList) {
                    res[popIndex][0] = leftLessNear;
                    res[popIndex][1] = i;
                }
            }
            if (!stack.isEmpty() && (arr[stack.peek().get(0)] == arr[i])) {
                stack.peek().add(i);
            } else {
                ArrayList<Integer> arrayList = new ArrayList<>();
                arrayList.add(i);
                stack.add(arrayList);
            }
        }
        //清空栈中的元素
        while (!stack.isEmpty()) {
            List<Integer> popList = stack.pop();
            int leftLessNear = stack.isEmpty() ? -1 : (stack.peek().get(stack.peek().size() - 1));
            for (int popIndex : popList) {
                res[popIndex][0] = leftLessNear;
                res[popIndex][1] = -1;
            }
        }
        return res;
    }

    public static void printArray(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + "  ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 5, 6, 3, 4};
        int[][] res = getNearLess(arr);
        printArray(res);
    }
}
