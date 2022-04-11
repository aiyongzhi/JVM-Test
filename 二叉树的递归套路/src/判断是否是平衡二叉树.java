public class 判断是否是平衡二叉树 {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    //判断平衡二叉树的条件是：所有子树全是平衡二叉树，则整颗树是平衡二叉树
    //以x为头的这颗树是平衡二叉树需满足条件
    //(1):x的左子树是平衡二叉树
    //(2):x的右子树是平衡二叉树
    //(3):x的左子树的高度与x右子树的高度差不超过1
    public static boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return process(root).isBalanced;
    }

    public static class Info {
        public boolean isBalanced;
        public int height;

        public Info(boolean is, int h) {
            isBalanced = is;
            height = h;
        }
    }

    public static Info process(TreeNode node) {
        if (node == null) {
            return new Info(true, 0);
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isBalanced = false;
        if (leftInfo.isBalanced && rightInfo.isBalanced &&
                (Math.abs(leftInfo.height - rightInfo.height) < 2)) {
            isBalanced = true;
        }
        return new Info(isBalanced, height);
    }
}
