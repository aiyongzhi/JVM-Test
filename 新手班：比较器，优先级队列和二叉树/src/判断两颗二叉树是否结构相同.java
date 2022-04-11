//力扣第100题
public class 判断两颗二叉树是否结构相同 {
    public class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public boolean isSame(TreeNode root1, TreeNode root2) {
        //有三种可能
        //1:root1==null&&root2==null
        //2:root1和root2有一个节点为null
        //3:root1和root2均不为空
        if (root1 == null ^ root2 == null) {
            return false;
        }

        if (root1 == null && root2 == null) {
            return true;
        }
        return root1.val == root2.val && isSame(root1.left, root2.left) &&
                isSame(root1.right, root2.right);
    }
}
