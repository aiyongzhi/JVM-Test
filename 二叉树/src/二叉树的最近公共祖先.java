public class 二叉树的最近公共祖先 {
    //经典力扣题：
    //力扣链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
    class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public class info {
        public boolean findO1;
        public boolean findO2;
        public TreeNode ancestor;

        public info(boolean findO1, boolean findO2, TreeNode ancestor) {
            this.findO1 = findO1;
            this.findO2 = findO2;
            this.ancestor = ancestor;
        }
    }

    public info process(TreeNode node, TreeNode o1, TreeNode o2) {
        if (node == null) {
            return new info(false, false, null);
        }
        info leftInfo = process(node.left, o1, o2);
        info rightInfo = process(node.right, o1, o2);
        boolean findO1 = node == o1 || leftInfo.findO1 || rightInfo.findO1;
        boolean findO2 = node == o2 || leftInfo.findO2 || rightInfo.findO2;
        //在以x为头的整颗树上有答案
        //可能性1:答案在左右子树上
        if (leftInfo.ancestor != null) {
            return new info(true, true, leftInfo.ancestor);
        }
        if (rightInfo.ancestor != null) {
            return new info(true, true, rightInfo.ancestor);
        }
        //可能性2:答案在x位置上
        if (findO1 && findO2) {
            return new info(true, true, node);
        }
        //找不到答案
        return new info(findO1, findO2, null);

    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return process(root, p, q).ancestor;
    }
}
