package 最小生成树;

import GrapTemplate.Edge;
import GrapTemplate.Graph;
import GrapTemplate.Node;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

//无向连通图
//思路：
//(1):从任意一个点出发，解锁与它链接的所有边
//(2):从所有被解锁的边中选择一条权值最小的边，去解锁新的点
//(3):重复(1)知道所有点全部解锁时结束
public class P算法 {
    //写一个比较器
    public static class minEdgeComparator implements Comparator<Edge> {
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static Set<Edge> prim(Graph graph) {
        //准备一个set记录所有被解锁的点
        //为了防止重复解锁边，用一个set记录所有被解锁的边
        HashSet<Node> nodeSet = new HashSet<>();//被解锁的点
        PriorityQueue<Edge> minHeap = new PriorityQueue<>(new minEdgeComparator());
        Set<Edge> result = new HashSet<>();
        for (Node node : graph.nodes.values()) {
            if (!nodeSet.contains(node)) {
                nodeSet.add(node);
                for (Edge edge : node.edges) {
                    minHeap.add(edge);
                }
                while (!minHeap.isEmpty()) {
                    Edge edge = minHeap.poll();
                    Node toNode = edge.to;
                    if (!nodeSet.contains(toNode)) {//可以有重复加边，因为这个if可以截断所有重复的边
                        nodeSet.add(toNode);
                        result.add(edge);
                        for (Edge next : toNode.edges) {
                            minHeap.add(next);
                        }
                    }
                }
            }
            break;
        }
        return result;
    }
}
