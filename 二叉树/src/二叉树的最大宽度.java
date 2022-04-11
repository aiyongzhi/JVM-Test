
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class 二叉树的最大宽度 {
    //求二叉树所有层中，节点个数最多层的节点数
    //二叉树的bfs的应用 下面写三种解法
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }


    //用一个队列，和一个记录层数的哈希表
    public static int maxWidth1(TreeNode head) {
        if (head == null) {
            return 0;
        }
        //key....表示节点 value...表示节点所在的层数
        HashMap<TreeNode, Integer> levelMap = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        levelMap.put(head, 1);
        queue.add(head);
        int curLevel = 1;
        int max = 0;
        int curLevelNodes = 0;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            int level = levelMap.get(cur);
            if (level == curLevel) {
                curLevelNodes++;
            } else {
                curLevel++;
                max = Math.max(max, curLevelNodes);
                curLevelNodes = 1;
            }
            if (cur.left != null) {
                levelMap.put(cur.left, level + 1);
                queue.add(cur.left);
            }
            if (cur.right != null) {
                levelMap.put(cur.right, level + 1);
                queue.add(cur.right);
            }
        }
        max = Math.max(max, curLevelNodes);
        return max;
    }

    //方法二：在统计每一层的时候，刷新每一层的结束位置
    public static int maxWidth2(TreeNode head) {
        if (head == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        int maxWidth = 0;
        TreeNode curEnd = head;//当前层的最后节点
        //在遍历当前层的时候，动态刷新出下一层的最后节点
        //当当前遍历到的节点是当前层的最后节点时，做一次总结
        TreeNode nextEnd = null;
        int curLevelNodes = 0;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            curLevelNodes++;
            if (cur.left != null) {
                nextEnd = cur.left;
                queue.add(cur.left);
            }
            if (cur.right != null) {
                nextEnd = cur.right;
                queue.add(cur.right);
            }
            //检查一次
            if (cur == curEnd) {
                maxWidth = Math.max(maxWidth, curLevelNodes);
                curLevelNodes = 0;
                curEnd = nextEnd;
            }
        }
        return maxWidth;
    }

    public static int maxWidth3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        //层序遍历会在遍历上一层的时候将下一层逐渐加入队列中
        //这个过程队列中既有上一层节点也有下一层节点
        //当上一层彻底遍历结束的时候，队列中就只有下一层的节点
        int maxWidth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            maxWidth = Math.max(maxWidth, size);
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
        }
        return maxWidth;
    }

    public static void main1(String[] args) {
        TreeNode head = new TreeNode(5);
        head.left = new TreeNode(3);
        head.right = new TreeNode(8);
        head.left.left = new TreeNode(2);
        head.left.right = new TreeNode(4);
        head.left.left.left = new TreeNode(1);
        head.right.left = new TreeNode(7);
        head.right.left.left = new TreeNode(6);
        head.right.right = new TreeNode(10);
        head.right.right.left = new TreeNode(9);
        head.right.right.right = new TreeNode(11);
        System.out.println(maxWidth1(head));
        System.out.println(maxWidth2(head));
    }

}
