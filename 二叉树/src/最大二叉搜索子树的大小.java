public class 最大二叉搜索子树的大小 {
    //子树：以一个节点为root，后面所有节点都要
    //给定一颗二叉树，求其最大二叉搜索子树的大小

    class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }

        public class Info {
            public int maxBSTSize;
            public boolean isBST;
            public int max;
            public int min;

            public Info(int maxBSTSize, boolean isBST, int max, int min) {
                this.maxBSTSize = maxBSTSize;
                this.isBST = isBST;
                this.max = max;
                this.min = min;
            }
        }

        /*        //树型dp
                public  Info process(TreeNode node){
                    if(node==null){
                        return new Info(0,true,Integer.MIN_VALUE,Integer.MAX_VALUE);
                    }
                    //向左右子树要信息
                    Info leftInfo=process(node.left);
                    Info rightInfo=process(node.right);
                    int max=node.val;
                    int min=node.val;
                    max=Math.max(max,Math.max(leftInfo.max,rightInfo.max));
                    min=Math.min(min,Math.min(leftInfo.min,rightInfo.min));
                    int maxBSTSize=0;
                    //假设最大搜索二叉子树，不以node为头
                    maxBSTSize=Math.max(leftInfo.maxBSTSize,rightInfo.maxBSTSize);
                    boolean isBST=false;
                    //如果node为头的是二叉搜索树，则更新数值
                    if(leftInfo.isBST&&rightInfo.isBST&&leftInfo.max<node.val
                    &&rightInfo.min>node.val){
                        isBST=true;
                        maxBSTSize=Math.max(maxBSTSize,leftInfo.maxBSTSize+ rightInfo.maxBSTSize+1);
                    }
                    return new Info(maxBSTSize,isBST,max,min);
                }
            }*/
        public Info process(TreeNode node) {
            //base case
            if (node == null) {
                return null;
            }
            Info leftInfo = process(node.left);
            Info rightInfo = process(node.right);
            int max = node.val;
            int min = node.val;
            if (leftInfo != null) {
                max = Math.max(max, leftInfo.max);
                min = Math.min(min, leftInfo.min);
            }
            if (rightInfo != null) {
                max = Math.max(max, rightInfo.max);
                min = Math.min(min, rightInfo.min);
            }
            int maxBSTSize = 0;
            boolean isBST = false;
            if (leftInfo != null) {
                maxBSTSize = Math.max(maxBSTSize, leftInfo.maxBSTSize);
            }
            if (rightInfo != null) {
                maxBSTSize = Math.max(maxBSTSize, rightInfo.maxBSTSize);
            }
            if (
                    (leftInfo != null ? leftInfo.isBST : true) && (rightInfo != null ? rightInfo.isBST : true)
                            && (leftInfo != null ? leftInfo.max < node.val : true) &&
                            (rightInfo != null ? rightInfo.min > node.val : true)
            ) {
                isBST = true;
                maxBSTSize = Math.max(maxBSTSize, (leftInfo != null ? leftInfo.maxBSTSize : 0)
                        + (rightInfo != null ? rightInfo.maxBSTSize : 0) + 1);
            }
            return new Info(maxBSTSize, isBST, max, min);
        }
    }
}
