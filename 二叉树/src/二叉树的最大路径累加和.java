public class 二叉树的最大路径累加和 {
    //二叉树的递归套路
    //两大类：
    //一：与x节点关：左右子树最大路径累加和的较大者
    //二：最大路径经过x节点
    //1:只有x自己
    //2:x+左子树从头开始的最大路径和
    //3:x+右子树从头开始的最大路径和
    //4:x+左子树从头开始的最大路径和+右子树从头开始的最大路径和
    //整合起来：整体需要两个条件：子树从头开始的最大路径和，和子树整体的最大路径和
    public class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static class Info {
        public int maxDistance;
        public int formHeadMaxDistance;

        public Info(int a, int b) {
            maxDistance = a;
            formHeadMaxDistance = b;
        }
    }

    //写递归用动态规划思想写递归
    public static Info process(TreeNode node) {
        //base case
        if (node == null) {
            return null;
        }
        //1:向左右子树要信息
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        //先整合，以x为头开始衍生的最大路径和:本质上是总情况中第二大类：经过x的情况
        int formHeadMaxDistance = node.val;
        formHeadMaxDistance = Math.max(Math.max((leftInfo == null ? Integer.MIN_VALUE : leftInfo.formHeadMaxDistance),
                        rightInfo == null ? Integer.MIN_VALUE : leftInfo.formHeadMaxDistance) + node.val,
                formHeadMaxDistance
        );
        if (leftInfo != null && rightInfo != null) {
            formHeadMaxDistance = Math.max(formHeadMaxDistance,
                    leftInfo.formHeadMaxDistance + rightInfo.formHeadMaxDistance + node.val);
        }
        //现在只需要整合出以x为头整棵树的最大路径和
        int maxDistance = Math.max(Math.max((leftInfo == null ? Integer.MIN_VALUE : leftInfo.maxDistance),
                rightInfo == null ? Integer.MIN_VALUE : rightInfo.maxDistance), formHeadMaxDistance);
        return new Info(maxDistance, formHeadMaxDistance);
    }

    public static int maxDistance(TreeNode root) {
        return process(root).maxDistance;
    }
}
