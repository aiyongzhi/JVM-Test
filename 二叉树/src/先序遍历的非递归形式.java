import java.util.Stack;

public class 先序遍历的非递归形式 {
    class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void preOrder(TreeNode head) {
        if (head == null) {
            return;
        }
        //开始遍历
        //用一个栈来实现先序遍历
        //规则1：从栈中弹出就打印
        //2:先压入右孩子
        //3:再压入左孩子
        //4:栈为空的时候停止
        Stack<TreeNode> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            System.out.print(cur.val + " ");
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }
}
