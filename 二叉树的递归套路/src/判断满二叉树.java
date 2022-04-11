public class 判断满二叉树 {
    //判断以x为头的整棵树是否是满二叉树
    //所有子树全是满二叉树，则一颗二叉树是满二叉树
    //(1)x的左子树是满二叉树
    //2)X的右子树是满二叉树
    //3)x的左子树高度等于右子树的高度
    public static class TreeNode {
        public int value;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            value = val;
        }
    }

    public static class Info {
        public boolean isFBT;
        public int height;

        public Info(boolean is, int h) {
            isFBT = is;
            height = h;
        }
    }

    public static boolean isFBT(TreeNode root) {
        if (root == null) {
            return true;
        }
        return process(root).isFBT;
    }

    public static Info process(TreeNode node) {
        if (node == null) {
            return new Info(true, 0);
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isFBT = false;
        if (leftInfo.isFBT && rightInfo.isFBT &&
                (leftInfo.height == rightInfo.height)) {
            isFBT = true;
        }
        return new Info(isFBT, height);
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(4);
        System.out.println(isFBT(head));
    }
}
