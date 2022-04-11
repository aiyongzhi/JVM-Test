import java.util.Stack;

public class 后序遍历的非递归形式 {
    class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void posOrder(TreeNode head) {
        if (head == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> result = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            result.push(cur);
            if (cur.left != null) {
                stack.push(cur.left);
            }
            if (cur.right != null) {
                stack.push(cur.right);
            }
        }
        while (!result.isEmpty()) {
            System.out.println(result.pop().val + " ");
        }
    }
}
