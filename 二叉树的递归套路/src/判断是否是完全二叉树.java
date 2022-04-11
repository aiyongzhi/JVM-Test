public class 判断是否是完全二叉树 {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    //完全二叉树的定义：除最后一层之外其它层为全满
    //最后一层为从左到右渐满的结构
    public static class Info {
        public boolean isFBT;
        public boolean isCBT;
        public int height;

        public Info(boolean a, boolean b, int c) {
            isFBT = a;
            isCBT = b;
            height = c;
        }
    }

    public static boolean isCBT(TreeNode root) {
        return process(root).isCBT;
    }

    public static Info process(TreeNode node) {
        //base case
        if (node == null) {
            return new Info(true, true, 0);
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        //高度为左子树高度和右子树高度的较大者+1
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        //满二叉树的条件:左子树是满二叉树，右子树是满二叉树，左子树高度=右子树高度
        boolean isFBT = leftInfo.isFBT && rightInfo.isFBT && leftInfo.height == rightInfo.height;
        boolean isCBT = false;
        if (isFBT) {
            isCBT = true;
        } else if (leftInfo.isCBT && rightInfo.isFBT && (leftInfo.height == rightInfo.height + 1)) {
            isCBT = true;
        } else if (leftInfo.isFBT && rightInfo.isFBT && (leftInfo.height == rightInfo.height + 1)) {
            isCBT = true;
        } else if (leftInfo.isFBT && rightInfo.isCBT && (leftInfo.height == rightInfo.height)) {
            isCBT = true;
        }
        return new Info(isFBT, isCBT, height);
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.right = new TreeNode(2);
        System.out.println(isCBT(head));
    }
}
