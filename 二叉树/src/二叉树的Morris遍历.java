public class 二叉树的Morris遍历 {
    public class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    //Morris遍历的规则
    //当前节点cur从头节点开始走
    //1：如果cur没有左子树，cur直接向右走
    //2：如果cur有左子树，用mostRight标记左子树最右侧的节点
    //2.1：如果mostRight为空，让mostRight的右指针指向cur，cur向左移动
    //2.2:如果mostRight不为空，让mostRight的右指针值向空，cur向右移动
    public static void Morris(TreeNode root) {
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
                    mostRight.right = null;
                    cur = cur.right;
                }
            } else {
                cur = cur.right;//中情况1
            }
        }
    }

    //用Morris实现先序遍历
    public static void MorrisPre(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode cur = root;//当前遍历到的节点
        TreeNode mostRight = null;//左子树的最右节点
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {//一定会来到cur两次
                //1:刷新出左子树最右节点
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                //2:判断两种情况
                if (mostRight.right == null) {//第一次来到cur
                    System.out.println(cur.val);
                    mostRight.right = cur;
                    cur = cur.left;
                } else {//第二次来到cur
                    mostRight.right = null;
                    cur = cur.right;
                }
            } else {//一定只来到一次
                System.out.println(cur.val);
                cur = cur.right;//中情况1
            }
        }
    }
    //
}
