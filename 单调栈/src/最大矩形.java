import java.util.Stack;

public class 最大矩形 {
    //技巧:压缩数组,加上单调栈
    //题目:给定一个矩阵,矩阵中每个位置只有'0'和'1'两种取值
    //把只含有'1'的子矩阵称为有效子矩阵
    //请返回矩阵中所有有效子矩阵中的最大面积是多少
    //用压缩数组技巧,以每一行为地基走直方图最大矩形面积
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

    public static int maximalRectangle(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int max = 0;
        int[] heights = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                heights[j] = matrix[i][j] == 1 ? (heights[j] + 1) : 0;
            }
            max = Math.max(max, largestRectangleArea(heights));
        }
        return max;
    }
}
