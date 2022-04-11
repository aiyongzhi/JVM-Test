import java.util.LinkedList;
import java.util.Queue;

public class 生成二叉树 {
    //给你一个数组，它是一颗二叉搜索树后序遍历的结果
    //请由这个数组生成一颗二叉搜索树
    //递归定义条件：用arr[L....R]范围内建出一棵树，并返回头节点
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static int binarySearch(int[] arr, int L, int R, int target) {
        //在这个数组中，查找小于target的最右下标处
        int ret = -1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] < target) {
                ret = mid;
                L = mid + 1;
            } else if (arr[mid] > target) {
                R = mid - 1;
            }
        }
        return ret;
    }

    public static TreeNode buildTree(int[] arr, int L, int R) {
        //base case 1
        if (L > R) {
            return null;
        }
        TreeNode root = new TreeNode(arr[R]);
        //base case 2
        if (L == R) {
            return root;
        }
        //找到小于root.val的最有区域
        int M = binarySearch(arr, L, R - 1, arr[R]);
        root.left = buildTree(arr, L, M);
        root.right = buildTree(arr, M + 1, R - 1);
        return root;
    }

    //测试一下
    public static void bfs(TreeNode head) {
        if (head == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            System.out.println(cur.val + " ");
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {3, 6, 5, 9, 11, 10, 8};
        TreeNode root = buildTree(arr, 0, arr.length - 1);
        bfs(root);
    }
}
