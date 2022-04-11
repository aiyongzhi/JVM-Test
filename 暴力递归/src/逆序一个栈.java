import java.util.Stack;

public class 逆序一个栈 {
    //请逆序一个栈,不能用其它的任何数据结构，只能用递归函数
    //通过每次移除栈底，在通过系统栈把最先移除的最后加入，最后移除的最先加入
    public static void reverse(Stack<Integer> stack) {
        //base case
        if (stack.isEmpty()) {
            return;
        }
        int cur = process(stack);
        reverse(stack);
        stack.add(cur);
    }

    //写一个递归函数：通过不断移除栈顶元素，拿到栈底的元素
    //递归含义：返回整个栈的栈顶其它元素保持不变
    public static int process(Stack<Integer> stack) {
        int cur = stack.pop();
        if (stack.isEmpty()) {
            return cur;
        } else {
            int last = process(stack);//拿到栈底元素后，一层层回代
            stack.add(cur);
            return last;
        }
    }

    public static void main(String[] args) {
        //Test
        Stack<Integer> stack = new Stack<>();
        stack.add(1);
        stack.add(2);
        stack.add(3);
        stack.add(4);
        reverse(stack);
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
