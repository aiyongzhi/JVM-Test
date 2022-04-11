public class 用Morris遍历实现二叉树的后序遍历 {
    public class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    //二叉树的后序遍历
    public static void MorrisPos(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode cur = root;//当前遍历到的节点
        TreeNode mostRight = null;//左子树的最右节点
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {//中情况2
                //1:刷新出左子树最右节点
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                //2:判断两种情况
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                } else {
                    printEdge(cur.left);
                    mostRight.right = null;
                    cur = cur.right;
                }
            } else {
                cur = cur.right;//中情况1
            }
        }
        printEdge(root);
        System.out.println();
    }

    //请逆序打印以head为头的树的右边界
    public static void printEdge(TreeNode head) {
        TreeNode tail = reverse(head);
        TreeNode cur = tail;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.right;
        }
        reverse(tail);
    }

    //逆序有边界并返回新的头
    public static TreeNode reverse(TreeNode from) {
        TreeNode prev = null;
        TreeNode next = null;
        while (from != null) {
            next = from.right;
            from.right = prev;
            prev = from;
            from = next;
        }
        return prev;
    }

    public static void main(String[] args) {

    }
}
