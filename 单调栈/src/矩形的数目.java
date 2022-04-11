import java.util.Stack;

public class 矩形的数目 {
    //技巧压缩矩阵+单调栈
    //题目给定一个二维矩阵matrix,matrix矩阵中只有'0'和'1'两种取值
    //请问矩阵中有多少个只含有1的子矩阵


    //写一个方法求出每一个直方图的子矩阵的数目
    public static int theNumberOfRectangle(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int sum = 0;
        Stack<Integer> stack = new Stack<>();//准备一个单调栈,内部存放的是元素的下标
        for (int i = 0; i < heights.length; i++) {
            //加入元素
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                int cur = stack.pop();
                if (heights[cur] > heights[i]) {
                    int leftLessNear = stack.isEmpty() ? -1 : stack.peek();
                    int down = Math.max(leftLessNear == -1 ? 0 : heights[leftLessNear],
                            heights[i]);
                    int len = leftLessNear == -1 ? i : (i - 1 - leftLessNear);
                    sum += num(len) * (heights[cur] - down);
                }
            }
            stack.add(i);
        }
        //清空栈
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            int leftLessNear = stack.isEmpty() ? -1 : stack.peek();
            int down = leftLessNear == -1 ? 0 : heights[leftLessNear];
            int len = heights.length - leftLessNear - 1;
            sum += num(len) * (heights[cur] - down);
        }
        return sum;
    }

    public static int num(int n) {
        return (n * (n + 1)) >> 1;
    }

    //以矩阵的每一行做地基，求出其子矩阵数目相加
    public static int theSumOfTheAllRectangle(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        //压缩数组的技巧
        int[] heights = new int[matrix[0].length];
        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                //遍历每一行生成heights直方图数组
                heights[j] = matrix[i][j] == '1' ? heights[j] + 1 : 0;
            }
            sum += theNumberOfRectangle(heights);
        }
        return sum;
    }

    //可以用数组来实现栈进行常数时间的优化
    public static void main(String[] args) {
    }
}
