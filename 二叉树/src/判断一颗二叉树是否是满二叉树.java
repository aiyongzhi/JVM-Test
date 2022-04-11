public class 判断一颗二叉树是否是满二叉树 {
    class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static class info {
        public int nodes;
        public int height;

        public info(int nodes, int height) {
            this.nodes = nodes;
            this.height = height;
        }
    }

    public static info process(TreeNode node) {
        if (node == null) {
            return new info(0, 0);
        }
        info leftInfo = process(node.left);
        info rightInfo = process(node.right);
        //整合出自己的信息后返回
        int nodes = leftInfo.nodes + rightInfo.nodes + 1;
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        return new info(nodes, height);
    }

    public static boolean isFullTree(TreeNode root) {
        info Info = process(root);
        int H = Info.height;
        int N = Info.nodes;
        return N == (1 << H) - 1;
    }
}
