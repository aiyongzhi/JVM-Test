import java.util.LinkedList;
import java.util.Queue;

public class 判断完全二叉树 {
    //给你一个二叉树，请判断其是否为完全二叉树
    //判断规则两条
    //1:如果遇到有右孩子，无左孩子的一定不是完全二叉树
    //2:如果遇到了左右不双全的节点（即：没有左孩子和右孩子的节点）,则之后的所有点，都是叶子节点
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static boolean isCBT(TreeNode root) {
        //默认空树是完全二叉树
        if (root == null) {
            return true;
        }
        //基于宽度优先遍历的判断规则
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode left = null;
        TreeNode right = null;
        boolean isMeet = false;//是否遇到了叶子节点
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            left = cur.left;
            right = cur.right;
            if ((right != null && left == null) || (isMeet && left != null)) {
                return false;
            }
            //设置好isMeet
            isMeet = left == null;
            if (left != null) {
                queue.add(left);
            }
            if (right != null) {
                queue.add(right);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        System.out.println(isCBT(head));
        head.right = new TreeNode(3);
        System.out.println(isCBT(head));
        head.left.right = new TreeNode(4);
        System.out.println(isCBT(head));
    }
}
