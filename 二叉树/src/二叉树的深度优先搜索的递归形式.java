public class 二叉树的深度优先搜索的递归形式 {
    //先序遍历
    public class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void process(TreeNode node) {
        if (node == null) {
            return;
        }
        //1:打印：是先序
        process(node.left);
        //2:打印：是中序
        process(node.right);
        //3:打印：是后序
    }
}
