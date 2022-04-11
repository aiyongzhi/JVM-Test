import java.util.Stack;

public class 中序遍历的非递归形式 {
    //原则使用一个栈
    //先让二叉树的整个左边界入栈
    //当左边界处理完的时候，去在右子树用相同的操作
    class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void inOrder(TreeNode head) {
        if (head == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = head;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                System.out.println(cur.val + " ");
                cur = cur.right;
            }
        }
        System.out.println();
    }
}
