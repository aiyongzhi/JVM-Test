package 图的遍历;

import GrapTemplate.Graph;
import GrapTemplate.Node;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

//图的宽度优先遍历
public class BFS {
    public static void bfs(Node node) {
        if (node == null) {
            return;
        }
        //规则：在进入队列的同时要进入set中
        //1:每次从队列中弹出一个，将他的next列表加入队列中去
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();//避免重复遍历，造成死循环
        queue.add(node);
        set.add(node);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.print(cur.val + " ");
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    set.add(next);
                    queue.add(next);
                }
            }
        }
    }
}
