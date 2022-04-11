
public class 判断一颗二叉树是否对称 {
    //利用判断两颗二叉树是否相等的思路一样
    //左边的左孩子等于右边的右孩子
    //左边的右孩子等于右边的左孩子
    class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public boolean isSame(TreeNode root1, TreeNode root2) {
        if (root1 == null ^ root2 == null) {
            return false;
        }
        if (root1 == null && root2 == null) {
            return true;
        }
        return root1.val == root2.val && isSame(root1.left, root2.right) &&
                isSame(root1.right, root2.left);
    }

    public boolean isSymmetric(TreeNode root) {
        return isSame(root, root);
    }
}
