import java.util.LinkedList;
import java.util.Queue;

public class 二叉树的序列化与反序列化 {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static TreeNode generateNode(String value) {
        if (value == null) {
            return null;
        }
        return new TreeNode(Integer.valueOf(value));
    }

    public static TreeNode buildByLevelQueue(Queue<String> levelList) {
        if (levelList == null || levelList.size() == 0) {
            return null;
        }
        TreeNode head = generateNode(levelList.poll());
        Queue<TreeNode> queue = new LinkedList<>();
        if (head != null) {
            queue.add(head);
        }
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            cur.left = generateNode(levelList.poll());
            cur.right = generateNode(levelList.poll());
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
        return head;
    }
}
