import javax.xml.soap.Node;

public class 找一个二叉树的后继节点 {
    //在二叉树的中序遍历中，在node节点之后打印的节点称为该节点的后继节点
    class TreeNode {
        public int val;
        public TreeNode parent;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    //我们默认该节点在一颗二叉树上面
    //两条规则：
    //1:如果该节点有右子树,则右子树的最左下节点就是
    //2:如果该节点没有有子树，则沿着父亲往上走，直到当前节点是父亲的左孩子
    public static TreeNode getSuccessTheAfterNode(TreeNode node) {
        if (node == null) {
            return null;
        }
        if (node.right != null) {
            return getTheMostLeftOne(node.right);
        } else {
            TreeNode parent = node.parent;
            while (parent != null && parent.left != node) {
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

    public static TreeNode getTheMostLeftOne(TreeNode head) {
        while (head.left != null) {
            head = head.left;
        }
        return head;
    }
}
