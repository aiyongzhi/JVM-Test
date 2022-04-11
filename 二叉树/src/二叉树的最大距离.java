import java.util.Scanner;

public class 二叉树的最大距离 {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static class Info {
        public int maxDistance;
        public int height;

        public Info(int maxDistance, int height) {
            this.maxDistance = maxDistance;
            this.height = height;
        }
    }

    public static Info process(TreeNode node) {
        //base case
        if (node == null) {
            return new Info(0, 0);
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int maxDistance = Math.max(
                Math.max(leftInfo.maxDistance, rightInfo.maxDistance),
                rightInfo.height + rightInfo.height + 1
        );
        return new Info(maxDistance, height);
    }

    public static int maxDistance(TreeNode root) {
        return process(root).maxDistance;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String cur = scanner.next();
    }
}
