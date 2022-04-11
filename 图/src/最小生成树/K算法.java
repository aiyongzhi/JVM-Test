package 最小生成树;

import GrapTemplate.Edge;
import GrapTemplate.Graph;
import GrapTemplate.Node;

import java.util.*;

//最小生成树是针对无向图
public class K算法 {
    //需要用到并查集
    public static class UnionFindSet {
        private HashMap<Node, Node> fathers;
        private HashMap<Node, Integer> sizeMap;//代表节点所代表集合的大小

        public UnionFindSet(Collection<Node> nodes) {
            fathers = new HashMap<>();
            sizeMap = new HashMap<>();
            for (Node cur : nodes) {
                fathers.put(cur, cur);
                sizeMap.put(cur, 1);
            }
        }

        //找到每个节点所在的集合的代表节点
        private Node findHead(Node cur) {
            Stack<Node> path = new Stack<>();
            while (cur != fathers.get(cur)) {
                path.add(cur);
                cur = fathers.get(cur);
            }
            //此时cur是代表节点，现在进行路径压缩
            while (!path.isEmpty()) {
                fathers.put(path.pop(), cur);
            }
            return cur;
        }

        public void union(Node from, Node to) {
            Node aHead = findHead(from);
            Node bHead = findHead(to);
            if (aHead != bHead) {
                int aSetSize = sizeMap.get(aHead);
                int bSetSize = sizeMap.get(bHead);
                Node big = aSetSize >= bSetSize ? aHead : bHead;
                Node small = big == aHead ? bHead : aHead;
                fathers.put(small, big);
                sizeMap.put(big, aSetSize + bSetSize);
                sizeMap.remove(small);
            }
        }

        public boolean isSameSet(Node a, Node b) {
            return findHead(a) == findHead(b);
        }
    }

    //定义一个比较器
    public static class minEdgeComparator implements Comparator<Edge> {
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    //最小生成树：请传参给我一个无向连通图
    public static Set<Edge> kruskalMST(Graph graph) {
        UnionFindSet unionSet = new UnionFindSet(graph.nodes.values());
        PriorityQueue<Edge> minHeap = new PriorityQueue<>(new minEdgeComparator());
        //先将小根堆初始化
        minHeap.addAll(graph.edges);
        Set<Edge> result = new HashSet<>();
        //每次从堆中弹出一条边，连接两个集合
        while (!minHeap.isEmpty()) {
            Edge cur = minHeap.poll();
            Node from = cur.from;
            Node to = cur.to;
            if (!unionSet.isSameSet(from, to)) {
                result.add(cur);
                unionSet.union(from, to);
            }
        }
        return result;
    }
}
