public class 判断平衡二叉树 {
    //力扣原题：https://leetcode-cn.com/problems/balanced-binary-tree/submissions/
    public class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int v) {
            val = v;
        }
    }

    public class Info {
        public boolean isBalanced;
        public int height;

        public Info(boolean i, int h) {
            isBalanced = i;
            height = h;
        }
    }

    //写一个递归函数，收集左右两树的信息，整合出自身的信息
    public Info process(TreeNode node) {
        //base case
        if (node == null) {
            return new Info(true, 0);
        }
        //向左右两子树拿信息
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        //整合出以x为头整棵树的信息
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isBalanced = leftInfo.isBalanced && rightInfo.isBalanced &&
                Math.abs(leftInfo.height - rightInfo.height) < 2;
        return new Info(isBalanced, height);
    }

    public boolean isBalanced(TreeNode root) {
        return process(root).isBalanced;
    }
}
