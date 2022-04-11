import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class 岛问题2 {
    //给你一个m*n的矩阵,一开始矩阵全部填上’0‘
    //然后给你一个操作列表，每次给一个坐标将改位置由'0'变成'1'
    //请返回一个list，里面元素是每次操作后的岛的数目
    //这题涉及到动态初始化问题
    //根据这题的业务场景设计并查集
    public static class UnionFindSet1 {
        private int[] fathers;//fathers[i]==k:表示i的父亲节点是k
        private int[] size;//size[i]==k:表示i作为代表节点时，所代表的集合的大小
        private int setSize;//并查集中集合的数目
        private int[] stack;
        private int col;
        private int row;

        public UnionFindSet1(int m, int n) {
            row = m;
            col = n;
            int len = m * n;
            fathers = new int[len];
            size = new int[len];
            stack = new int[len];
            setSize = 0;
        }

        //将二维数组的下标转为一维数组的下标
        private int getIndex(int i, int j) {
            return i * col + j;
        }

        private boolean add(int index) {
            //表示曾经初始化过
            if (size[index] != 0) {
                return false;
            }
            fathers[index] = index;
            size[index] = 1;
            setSize++;
            return true;
        }

        //方法：找到元素的代表节点
        private int findHead(int i) {
            int index = 0;
            while (i != fathers[i]) {
                stack[index++] = i;
                i = fathers[i];
            }
            //i已经到了代表节点
            //加速
            //让沿途节点全部指向代表节点
            for (index--; index >= 0; index--) {
                fathers[stack[index]] = i;
            }
            return i;
        }

        private void union(int row1, int col1, int row2, int col2) {
            //检查越界
            if (row1 < 0 || row1 == row || col1 < 0 || col1 == col || row2 < 0 || row2 == row || col2 < 0 || col2 == col) {
                return;
            }
            //都是1才能合并
            int a = getIndex(row1, col1);
            int b = getIndex(row2, col2);
            if (size[a] == 0 || size[b] == 0) {
                return;
            }
            //才可以合并
            int aHead = findHead(a);
            int bHead = findHead(b);
            if (aHead != bHead) {
                //第二个加速点
                if (size[aHead] >= size[bHead]) {
                    size[aHead] += size[bHead];
                    fathers[bHead] = aHead;
                } else {
                    size[bHead] += size[aHead];
                    fathers[aHead] = bHead;
                }
                setSize--;
            }
        }

        private int size() {
            return setSize;
        }

        //方法的功能：给你一个坐标，将它初始化好，并且合并，并返回大小
        public int connect(int i, int j) {
            int index = getIndex(i, j);
            if (add(index)) {
                union(i, j, i - 1, j);
                union(i, j, i + 1, j);
                union(i, j, i, j - 1);
                union(i, j, i, j + 1);
            }
            return this.size();
        }
    }

    public static List<Integer> getLandCount1(int m, int n, int[][] options) {
        List<Integer> ans = new ArrayList<>();
        UnionFindSet1 unionSet = new UnionFindSet1(m, n);
        for (int[] option : options) {
            ans.add(unionSet.connect(option[0], option[1]));
        }
        return ans;
    }

    //用数组实现并查集初始化的时候空间复杂度是O(m*n),但如果m*k>>k的时候(k为options的长度)
    //会浪费大量空间,我们可以只将变成1的初始化更好
    public static List<Integer> getLandCount2(int m, int n, int[][] options) {
        List<Integer> ans = new ArrayList<>();
        UnionFindSet2 unionSet = new UnionFindSet2();
        for (int[] option : options) {
            ans.add(unionSet.connect(option[0], option[1]));
        }
        return ans;
    }

    public static class UnionFindSet2 {
        //用哈希表来储存每个节点的父节点
        private HashMap<String, String> fathers;
        private HashMap<String, Integer> sizeMap;
        //并查集中不同集合的数目
        private int size;

        public UnionFindSet2() {
            fathers = new HashMap<>();
            sizeMap = new HashMap<>();
            size = 0;
        }

        //找到每个节点的代表节点
        private String findHead(String cur) {
            Stack<String> path = new Stack<>();
            while (!cur.equals(fathers.get(cur))) {
                path.add(cur);
                cur = fathers.get(cur);
            }
            //cur已经到代表节点了
            //让沿途的节点全部指向代表节点
            while (!path.isEmpty()) {
                fathers.put(path.pop(), cur);
            }
            return cur;
        }

        //合并操作
        private void union(String a, String b) {
            String aHead = findHead(a);
            String bHead = findHead(b);
            if (!aHead.equals(bHead)) {
                int aHeadSize = sizeMap.get(aHead);
                int bHeadSize = sizeMap.get(bHead);
                String big = aHeadSize >= bHeadSize ? aHead : bHead;
                String small = big.equals(aHead) ? bHead : aHead;
                sizeMap.put(big, aHeadSize + bHeadSize);
                fathers.put(small, big);
                size--;
            }
        }

        //给你一个坐标把该位置的'0'->'1',进行合并返回集合的个数
        public int connect(int row, int col) {
            String pos = String.valueOf(row) + "_" + String.valueOf(col);
            if (!sizeMap.containsKey(pos)) {
                fathers.put(pos, pos);
                sizeMap.put(pos, 1);
                size++;
                String up = String.valueOf(row - 1) + "_" + String.valueOf(col);
                String down = String.valueOf(row + 1) + "_" + String.valueOf(col);
                String left = String.valueOf(row) + "_" + String.valueOf(col - 1);
                String right = String.valueOf(row) + "_" + String.valueOf(col + 1);
                if (sizeMap.containsKey(up)) {
                    union(pos, up);
                }
                if (sizeMap.containsKey(down)) {
                    union(pos, down);
                }
                if (sizeMap.containsKey(left)) {
                    union(pos, left);
                }
                if (sizeMap.containsKey(right)) {
                    union(pos, right);
                }
            }
            return size;
        }

    }

    public static void main(String[] args) {
        int m = 3;
        int n = 5;
        int[][] options = {{0, 0}, {1, 1}, {1, 0}};
        List<Integer> ans1 = getLandCount1(m, n, options);
        for (Integer a : ans1) {
            System.out.println(a);
        }
        List<Integer> ans2 = getLandCount2(m, n, options);
        for (Integer a : ans2) {
            System.out.println(a);
        }
    }
}
