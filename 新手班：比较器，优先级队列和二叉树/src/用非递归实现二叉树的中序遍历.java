import java.util.Stack;

public class 用非递归实现二叉树的中序遍历 {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    //规则cur从头开始
    //(1)cur!=null 在栈中压入cur，cur=cur.left扫描左边界
    //(2)cur==null 弹出栈顶元素，并打印，cur来到弹出元素的右子树上
    public static void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.add(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                System.out.print(cur.val + " ");
                cur = cur.right;
            }
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
        inorder(head);
    }

    public static void posOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        posOrder(root.left);
        posOrder(root.right);
        System.out.println(root.val);
    }
}
