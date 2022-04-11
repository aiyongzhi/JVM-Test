import java.util.Stack;

public class 非递归实现二叉树先序遍历 {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    //自己压栈来实现
    //规则
    //1:弹出栈顶
    //2:有右孩子压入右孩子
    //3:有左孩子压入左孩子
    //4:先右再左
    public static void pre(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            System.out.print(cur.val + " ");
            if (cur.right != null) {
                stack.add(cur.right);
            }
            if (cur.left != null) {
                stack.add(cur.left);
            }
        }
        System.out.println();
    }
}
