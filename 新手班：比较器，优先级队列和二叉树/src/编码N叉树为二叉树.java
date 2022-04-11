
import java.util.ArrayList;
import java.util.List;

public class 编码N叉树为二叉树 {
    //现在有一颗N叉树，请你设计一个算法，把N叉树编码成二叉树
    //要保证N叉树和编码后的二叉树是一一对应关系，即编码后的二叉树还能还原成原来的N叉树且保持一一对应关系

    //N叉树的节点
    public static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, List<Node> children) {
            this.val = val;
            this.children = children;
        }
    }

    //二叉树的节点
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    //写一个类进行编码，将N叉树编码成二叉树
    public static class Codec {
        public Node root;

        public Codec(Node root) {
            this.root = root;
        }

        public TreeNode encode() {
            if (root == null) {
                return null;
            }
            TreeNode head = new TreeNode(root.val);
            head.left = en(root.children);
            return head;
        }

        private TreeNode en(List<Node> children) {
            TreeNode head = null;
            TreeNode cur = null;
            for (Node child : children) {
                TreeNode tNode = new TreeNode(child.val);
                if (head == null) {
                    head = tNode;
                } else {
                    cur.right = tNode;
                }
                cur = tNode;
                cur.left = en(child.children);
            }
            return head;
        }
    }

    //反序列化：将二叉树编码回N叉树
    public static class DeCodec {
        public TreeNode root;

        public DeCodec(TreeNode root) {
            this.root = root;
        }

        //
        public Node decode() {
            if (root == null) {
                return null;
            }
            return new Node(root.val, de(root.left));
        }

        private List<Node> de(TreeNode head) {
            List<Node> children = new ArrayList<>();
            while (head != null) {
                Node child = new Node(head.val, de(head.left));
                children.add(child);
                head = head.right;
            }
            return children;
        }
    }

}