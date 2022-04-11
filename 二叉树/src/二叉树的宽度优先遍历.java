import java.util.LinkedList;
import java.util.Queue;

public class 二叉树的宽度优先遍历 {
    //方法论：准备一个队列:在遍历每一层的时候，让下一层的节点入队列
    //规则1：每次从队列中弹出一个节点
    //2:有左孩子的让左孩子入队列
    //3:有有孩子则让右孩子入队列
    class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void bfs(TreeNode head) {
        if (head == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            System.out.println(cur.val);
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
        System.out.println();
    }
}
