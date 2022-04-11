public class 验证搜索二叉树 {
    class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static class Info {
        public boolean isBST;
        public int max;
        public int min;

        public Info(boolean isBST, int max, int min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }

    public static Info process(TreeNode node) {
        if (node == null) {
            return null;
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        //整合出自己的信息后返回
        //1:先整合出整棵树的最大值和最小值
        int max = node.val;
        int min = node.val;
        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
        }
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
        }
        //再整合出整棵树是否是搜索二叉树
        boolean isBST = false;
        if (
                (leftInfo != null ? (leftInfo.isBST && leftInfo.max < node.val) : true)
                        && (rightInfo != null ? (rightInfo.isBST && rightInfo.min > node.val) : true)
        ) {
            isBST = true;
        }
        return new Info(isBST, max, min);
    }

    public static boolean isValidBST(TreeNode root) {
        return process(root).isBST;
    }

}
