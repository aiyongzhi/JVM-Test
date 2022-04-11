public class 二叉树的最大深度 {
    //给定一颗二叉树返回二叉树的最大深度
    class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    //递归函数
    //递归含义：以node为头的这棵树的最大深度
    public int process(TreeNode node) {
        //base case
        if (node == null) {
            return 0;
        }
        return Math.max(process(node.left), process(node.right)) + 1;
    }

    public int getMaxDepth(TreeNode root) {
        return process(root);
    }

}
