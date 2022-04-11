import java.util.Stack;

public class 非递归实现二叉树的后序遍历 {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void posOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> help = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            help.add(cur);
            if (cur.left != null) {
                stack.add(cur.left);
            }
            if (cur.right != null) {
                stack.add(cur.right);
            }
        }
        while (!help.isEmpty()) {
            System.out.print(help.pop().val + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(4);
        head.left.right = new TreeNode(5);
        head.right.left = new TreeNode(6);
        head.right.right = new TreeNode(7);
        posOrder(head);
    }
}
