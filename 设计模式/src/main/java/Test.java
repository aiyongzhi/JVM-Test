import java.util.HashMap;
import java.util.Stack;

public class Test {
    boolean isMatch(char[] str) {
        int N = str.length;
        if (N % 2 == 1) {
            return false;
        }
        //准备一个表记录括号的比配关系
        //例如'('->')'  '['->']'  ’{‘->'}'
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        //准备一个栈
        Stack<Character> stack = new Stack<>();
        //最优解：只遍历一次数组
        for (int i = 0; i < N; i++) {
            char ch = str[i];
            if (map.containsKey(ch)) {//说明已经遍历到了右侧的括号
                if (stack.isEmpty() || stack.peek() != map.get(ch)) {//从栈中弹出左侧的括号发现匹配不上
                    return false;
                }
                stack.pop();
            } else {
                stack.add(ch);//加入左侧括号
            }
        }
        return stack.isEmpty();//最终如果栈为空，则一定
    }
}
