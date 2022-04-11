
public class 判断搜索二叉树 {
    public static class TreeNode {
        public int value;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            value = val;
        }
    }

    //任意一颗树为搜索二叉树的条件是
    //1)左子树是搜索二叉树
    //2)右子树是搜索二叉树
    //3)左子树的最大值小于x的值，右子树的最小值大于x节点的值
    public static class Info {
        public boolean isBST;
        public int max;
        public int min;

        public Info(boolean a, int b, int c) {
            isBST = a;
            max = b;
            min = c;
        }
    }

    public static boolean isBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return process(root).isBST;
    }

    public static Info process(TreeNode node) {
        if (node == null) {
            return null;
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int max = node.value;
        int min = node.value;
        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
        }
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
        }

        boolean isBST = false;
        if ((leftInfo == null ? true : leftInfo.isBST) &&
                (rightInfo == null ? true : rightInfo.isBST) &&
                (leftInfo == null ? true : leftInfo.max < node.value) &&
                (rightInfo == null ? true : rightInfo.min > node.value)) {
            isBST = true;
        }
        return new Info(isBST, max, min);
    }
}
