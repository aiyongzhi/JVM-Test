public class 二叉树的最大距离 {
    //给定你一个二叉树的头节点head,二叉树中任意两个节点都有距离，请返回二叉树的最大距离。
    //这个最大距离即路径上的节点个数

    //最大路径经过x，最大距离=左树高度+右树高度+1
    //最大路径与x无关，最大距离=左树最大距离，右树的最大距离的较大者
    public static class TreeNode {
        public int value;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    public static class Info {
        public int height;
        public int maxDistance;

        public Info(int a, int b) {
            height = a;
            maxDistance = b;
        }
    }

    public static int maxDistance(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return process(root).maxDistance;
    }

    public static Info process(TreeNode node) {
        if (node == null) {
            return new Info(0, 0);
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int maxDistance = Math.max(Math.max(leftInfo.maxDistance, rightInfo.maxDistance),
                leftInfo.height + rightInfo.height + 1);
        return new Info(height, maxDistance);
    }
}
