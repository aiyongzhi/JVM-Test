import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class 朋友圈 {
    //给你一个n*n的矩阵m,表示人相互认识的关系m[i][j]表示i与j的认识关系
    //这个正方形矩阵一定沿对角线对称，即如果m[i][j]==1表示i与j相互认识，m[i][j]==0表示i不认识j
    //我们定义人自己一定和自己认识即对角线全是1
    //如果i认识j则，i和j的朋友圈会合并成一个朋友圈
    //请问这个矩阵所表示的朋友圈的数目
    //一定要注意：算法的原型是一定的，但是不同的题目可能需要对算法进行改写
    //比如这里的并查集要自己改写
    public static int getCircleNum(int[][] m) {
        if (m == null || m.length == 0) {
            return 0;
        }
        int N = m.length;
        //初始化并查集
        UnionFindSet unionSet = new UnionFindSet(N);
        for (int i = 0; i < m.length; i++) {
            for (int j = i + 1; j < m.length; j++) {
                if (m[i][j] == 1) {
                    unionSet.union(i, j);
                }
            }
        }
        return unionSet.setSize();
    }

    //用数组来实现并查集
    //根据这个题目改写出最快的算法
    public static class UnionFindSet {
        //int[i]==k表示i的父亲是k
        private int[] fathers;
        //int[i]==k i为代表节点时，i所代表的集合大小为k
        //如果i不是代表节点则没有意义
        private int[] size;
        private int[] stack;//辅助栈用来实现，并查集的把路径变扁平
        private int setSize;//并查集中集合的数目

        public UnionFindSet(int N) {
            fathers = new int[N];
            size = new int[N];
            stack = new int[N];
            for (int i = 0; i < N; i++) {
                fathers[i] = i;
                size[i] = 1;
            }
            setSize = N;
        }

        private int findHead(int i) {
            int index = 0;
            while (i != fathers[i]) {
                stack[index++] = i;
                i = fathers[i];
            }
            //将路径上的所有人的父亲都设置为代表节点
            for (index--; index >= 0; index--) {
                fathers[stack[index]] = i;
            }
            return i;
        }

        //将i人的朋友圈与j人的朋友圈合并
        public void union(int i, int j) {
            int iHead = findHead(i);
            int jHead = findHead(j);
            if (iHead != jHead) {
                if (size[iHead] >= size[jHead]) {
                    size[iHead] += size[jHead];
                    fathers[jHead] = iHead;
                } else {
                    size[jHead] += size[iHead];
                    fathers[iHead] = jHead;
                }
                setSize--;
            }
        }

        //拿到并查集中集合的个数
        public int setSize() {
            return setSize;
        }
    }
}
