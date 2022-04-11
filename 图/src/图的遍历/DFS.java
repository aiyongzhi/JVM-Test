package 图的遍历;

import GrapTemplate.Node;

import java.util.HashSet;
import java.util.Stack;

//图的深度优先遍历
public class DFS {
    //利用非递归来写
    public static void dfs(Node head) {
        //手动压栈，注意我们规定，入栈的时候就处理
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();//去重，防止走重复节点
        stack.add(head);
        set.add(head);
        System.out.print(head.val + " ");
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    stack.add(cur);//向深处走，需要回退
                    stack.add(next);
                    set.add(next);
                    System.out.println(next.val + " ");
                    break;//找到了一条可以继续深入的路，break跳出找路环节
                }
            }
        }
    }
}
