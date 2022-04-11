public class 最大二叉搜索子树 {
    //给你一个头节点head，请返回最大二叉搜索子树的大小
    //大小为节点个数

    //分析得到答案的可能性
    //(1)与x无关，该情况一定会存在
    //左子树上的最大二叉搜索子树的大小和右子树上的最大二叉搜索子树的大小的较大者
    //(2)与x有关，x是最大二叉搜索子树的头节点
    //这种情况需满足x为头的树是二叉搜索树的情况下成立
    //此时左子树的最大二叉搜索子树就是左子树
    //右子树的最大二叉搜索子树就是右子树
    //此情况下为左数二叉搜索子树，与右子树最大二叉搜索子树的和+1

    public static class TreeNode {
        public int value;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            value = val;
        }
    }

    public static class Info {
        public int maxBSTSize;
        public boolean isBST;
        public int max;
        public int min;

        public Info(int a, boolean b, int c, int d) {
            maxBSTSize = a;
            isBST = b;
            max = c;
            min = d;
        }
    }

    public static int maxBSTSize(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return process(root).maxBSTSize;
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
        if ((leftInfo == null ? true : leftInfo.isBST) && (
                rightInfo == null ? true : rightInfo.isBST
        ) && (leftInfo == null ? true : leftInfo.max < node.value) &&
                (rightInfo == null ? true : rightInfo.min > node.value)) {
            isBST = true;
        }
        int p1 = Math.max(leftInfo == null ? Integer.MIN_VALUE : leftInfo.maxBSTSize,
                rightInfo == null ? Integer.MIN_VALUE : rightInfo.maxBSTSize);
        int p2 = 0;
        if (isBST) {
            p2 = (leftInfo == null ? 0 : leftInfo.maxBSTSize) + (rightInfo == null ? 0 : rightInfo.maxBSTSize)
                    + 1;
        }
        int maxBSTSize = Math.max(p1, p2);
        return new Info(maxBSTSize, isBST, max, min);
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(6);
        head.right = new TreeNode(3);
        head.left = new TreeNode(4);
        head.left.left = new TreeNode(9);
        head.left.right = new TreeNode(8);
        head.left.right.left = new TreeNode(7);
        head.left.right.right = new TreeNode(10);
        head.left.right.left.left = new TreeNode(5);
        System.out.println(maxBSTSize(head));
    }
}
