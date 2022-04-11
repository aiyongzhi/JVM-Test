import java.util.Stack;

public class 表达式求值问题 {
    //给你一个表达式，现在我们规定表达式只能有整数，加减乘除运算符和括号
    //我们规定：括号可以多重嵌套，请返回整个表达式运算后的结果

    //我们需要一个递归函数，process(i...)表示从i位置开始向后算，遇到右括号或终止位置停止
    //返回整个表达式运算后的结果
    //返回一个数组bru
    //其中bru[0] 表示整个递归过程算出的结果
    //bur[1] 表示整个递归过程算到哪里了
    public static int[] f(int i, char[] str) {
        Stack<String> stack = new Stack<>();
        int cur = 0;
        int[] bra = null;
        //从i位置开始往下撸
        while (i < str.length && str[i] != ')') {
            if (str[i] >= '0' && str[i] <= '9') {
                cur = cur * 10 + str[i++] - '0';
            }
            //否则遇到的是运算符或者括号
            else if (str[i] != '(') {//遇到的是运算符
                addNum(stack, cur);
                stack.add(String.valueOf(str[i++]));
                cur = 0;
            } else {//遇到的是(''
                bra = f(i + 1, str);
                cur = bra[0];
                i = bra[1] + 1;
            }
        }
        //最后要么是遇到右括号要么是终止了，但最后一个数据没有加入
        addNum(stack, cur);
        return new int[]{getNum(stack), i};
    }

    //写一个函数，功能是：遇到运算符了，怎样处理cur
    public static void addNum(Stack<String> stack, int cur) {
        if (!stack.isEmpty()) {
            String top = stack.peek();
            if (top.equals("*") || top.equals("/")) {//遇到的是乘和除号
                int ans = 0;
                String symbol = stack.pop();
                int num = Integer.parseInt(stack.pop());
                ans = symbol.equals("*") ? (num * cur) : (num / cur);
                stack.add(String.valueOf(ans));
                return;
            }
        }
        stack.add(String.valueOf(cur));
    }

    //写一个函数getNum功能是计算一个将整个栈中的元素经行计算并返回结果
    public static int getNum(Stack<String> stack) {
        int num1 = 0;
        int num2 = 0;
        int ans = 0;
        while (stack.size() > 1) {
            num1 = Integer.parseInt(stack.pop());
            String symbol = stack.pop();
            num2 = Integer.parseInt(stack.pop());
            ans = symbol.equals("+") ? num1 + num2 : num2 - num1;
            stack.add(String.valueOf(ans));
        }
        return Integer.parseInt(stack.pop());
    }

    public static int calculate(String str) {
        if (str == null || str.length() == 0) {
            throw new RuntimeException("This string is empty:!");
        }
        char[] chs = str.toCharArray();
        int[] res = f(0, chs);
        return res[0];
    }

    public static void main(String[] args) {
        String str = "(1+3*(2+5)-2)";
        System.out.println(calculate(str));
    }
}
