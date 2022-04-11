import java.util.*;

public class 生成二叉树 {
    //力扣原题：order-traversal/submissions/https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-in
    //利用先序数组和中序数组生成二叉树
    public class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
