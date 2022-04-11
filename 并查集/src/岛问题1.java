import java.util.*;

public class 岛问题1 {
    //给你一个二维矩阵board
    //board[i][j]=='1',说明这个位置是陆地
    //board[i][j]=='0':说明这个位置是海
    //我们把矩阵中上下左右相连的’1‘，称为一个岛
    //请问改矩阵中岛的数目
    public static int getLandCount1(char[][] board) {
        if (board == null || board.length == 0) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '1') {
                    ans++;
                    inflect(board, i, j);
                }
            }
        }
        return ans;
    }

    //广度优先遍历bfs,感染方法，遇到一个’1‘，岛数++,把周围连成一片的’1‘，全部染成'0'
    //递归含义:从(i,j)位置开始，把与它连成一片的'1',全部染成'0'
    public static void inflect(char[][] board, int i, int j) {
        //base case
        if (i < 0 || i == board.length || j < 0 || j == board[0].length || board[i][j] != '1') {
            return;
        }
        //board[i][j]=='1'
        board[i][j] = 0;
        inflect(board, i - 1, j);
        inflect(board, i + 1, j);
        inflect(board, i, j - 1);
        inflect(board, i, j + 1);
    }

    //并查集用哈希表的形式
    public static class Dot {
    }

    public static int getLandCount2(char[][] board) {
        if (board == null || board.length == 0) {
            return 0;
        }
        int n = board.length;
        int m = board[0].length;
        Dot[][] dots = new Dot[n][m];
        List<Dot> dotList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == '1') {
                    dots[i][j] = new Dot();
                    dotList.add(dots[i][j]);
                }
            }
        }
        UnionFindSet1<Dot> unionSet = new UnionFindSet1<>(dotList);
        //将每个位置和上位置合并，和下位置合并
        //合并第一行
        for (int col = 1; col < m; col++) {
            if (board[0][col - 1] == '1' && board[0][col] == '1') {
                unionSet.union(dots[0][col - 1], dots[0][col]);
            }
        }
        //合并第一列
        for (int row = 1; row < n; row++) {
            if (board[row - 1][0] == '1' && board[row][0] == '1') {
                unionSet.union(dots[row - 1][0], dots[row][0]);
            }
        }
        //合并普遍位置
        for (int row = 1; row < n; row++) {
            for (int col = 1; col < m; col++) {
                if (board[row][col] == '1') {
                    if (board[row - 1][col] == '1') {
                        unionSet.union(dots[row][col], dots[row - 1][col]);
                    }
                    if (board[row][col - 1] == '1') {
                        unionSet.union(dots[row][col], dots[row][col - 1]);
                    }
                }
            }
        }
        return unionSet.size();
    }

    public static class Node<V> {
        public V value;

        public Node(V v) {
            value = v;
        }
    }

    public static class UnionFindSet1<V> {
        //每个样本所对应的节点
        HashMap<V, Node<V>> nodes;
        //每个节点所对应的父节点
        HashMap<Node<V>, Node<V>> parents;
        //代表节点所代表的集合的大小
        HashMap<Node<V>, Integer> sizeMap;

        public UnionFindSet1(List<V> list) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V value : list) {
                Node<V> newNode = new Node<>(value);
                nodes.put(value, newNode);
                parents.put(newNode, newNode);
                sizeMap.put(newNode, 1);
            }
        }

        //重要的思路是：我们用代表节点来代表一个集合
        //写一个方法，找到每一个节点所在的集合的代表节点
        //重要优化点1)并把沿途的所有节点都直接指向代表节点，这样就不需要每次都遍历找代表节点
        //第一次后，其它所有次找代表节点时都可以直接找到
        private Node<V> findFather(Node<V> cur) {
            //path记录沿途的所有节点
            Stack<Node<V>> path = new Stack<>();
            while (cur != parents.get(cur)) {
                path.add(cur);
                cur = parents.get(cur);
            }
            //在return cur之前，让沿途的节点全指向代表节点
            while (!path.isEmpty()) {
                parents.put(path.pop(), cur);
            }
            return cur;
        }

        //合并两个元素所在的集合
        public void union(V a, V b) {
            Node<V> aHead = findFather(nodes.get(a));
            Node<V> bHead = findFather(nodes.get(b));
            if (aHead != bHead) {
                int aSetSize = sizeMap.get(aHead);
                int bSetSize = sizeMap.get(bHead);
                Node<V> big = aSetSize >= bSetSize ? aHead : bHead;
                Node<V> small = big == aHead ? bHead : aHead;
                parents.put(small, big);
                sizeMap.put(big, aSetSize + bSetSize);
                sizeMap.remove(small);
            }
        }

        public int size() {
            return sizeMap.size();
        }
    }

    //第三种方法:用数组实现并查集更快
    public static int getLandCount3(char[][] board) {
        if (board == null || board[0].length == 0) {
            return 0;
        }
        UnionFindSet2 unionSet = new UnionFindSet2(board);
        //将每个位置和上位置合并，和下位置合并
        //合并第一行
        int n = board.length;
        int m = board[0].length;
        for (int col = 1; col < m; col++) {
            if (board[0][col - 1] == '1' && board[0][col] == '1') {
                unionSet.union(0, col - 1, 0, col);
            }
        }
        //合并第一列
        for (int row = 1; row < n; row++) {
            if (board[row - 1][0] == '1' && board[row][0] == '1') {
                unionSet.union(row - 1, 0, row, 0);
            }
        }
        //合并普遍位置
        for (int row = 1; row < n; row++) {
            for (int col = 1; col < m; col++) {
                if (board[row][col] == '1') {
                    if (board[row - 1][col] == '1') {
                        unionSet.union(row, col, row - 1, col);
                    }
                    if (board[row][col - 1] == '1') {
                        unionSet.union(row, col, row, col - 1);
                    }
                }
            }
        }
        return unionSet.size();
    }

    public static class UnionFindSet2 {
        private int[] fathers;
        private int[] size;
        private int[] stack;
        private int row;
        private int col;
        private int setSize;

        public UnionFindSet2(char[][] board) {
            setSize = 0;
            row = board.length;
            col = board[0].length;
            fathers = new int[row * col];
            size = new int[row * col];
            stack = new int[row * col];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (board[i][j] == '1') {
                        int index = getIndex(i, j);
                        fathers[index] = index;
                        size[index] = 1;
                        setSize++;
                    }
                }
            }
        }

        //将矩阵中的下标对应到一维数组中去
        private int getIndex(int i, int j) {
            return i * col + j;
        }

        private int findHead(int i) {
            int index = 0;
            while (i != fathers[i]) {
                stack[index++] = i;
                i = fathers[i];
            }
            //i是head代表节点
            for (--index; index >= 0; index--) {
                fathers[stack[index]] = i;
            }
            return i;
        }

        public void union(int row1, int col1, int row2, int col2) {
            int a = getIndex(row1, col1);
            int b = getIndex(row2, col2);
            int aHead = findHead(a);
            int bHead = findHead(b);
            if (aHead != bHead) {
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

        public int size() {
            return setSize;
        }
    }

    public static void main(String[] args) {
        char[][] board1 = {{'1', '0', '1', '1', '0'}, {'0', '1', '1', '0', '1'},
                {'1', '1', '0', '0', '1'}};
        char[][] board2 = {{'1', '0', '1', '1', '0'}, {'0', '1', '1', '0', '1'},
                {'1', '1', '0', '0', '1'}};
        char[][] board3 = {{'1', '0', '1', '1', '0'}, {'0', '1', '1', '0', '1'},
                {'1', '1', '0', '0', '1'}};
        System.out.println(getLandCount1(board1));
        System.out.println(getLandCount2(board2));
        System.out.println(getLandCount3(board3));
    }

}
