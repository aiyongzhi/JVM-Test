package 单源最短路径;

import GrapTemplate.Edge;
import GrapTemplate.Graph;
import GrapTemplate.Node;

import java.util.HashMap;
import java.util.HashSet;

//为了防止重复加入一个元素，要准备一个set来记录已经处理过的点
public class dijkstra1 {
    public static HashMap<Node, Integer> dijkstra(Node from) {
        HashSet<Node> set = new HashSet<>();//记录有哪些处理过的点
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        HashMap<Node, DisNode> disNodeMap = new HashMap<>();
        GreaterHeap minHeap = new GreaterHeap();

        distanceMap.put(from, 0);
        DisNode node = new DisNode(from, distanceMap.get(from));
        minHeap.push(node);
        disNodeMap.put(from, node);
        while (!minHeap.isEmpty()) {
            Node minNode = minHeap.pop().node;
            set.add(minNode);
            int distance = distanceMap.get(minNode);
            for (Edge cur : minNode.edges) {
                Node toNode = cur.to;
                if (!distanceMap.containsKey(toNode)) {
                    distanceMap.put(toNode, distance + cur.weight);
                    DisNode newNode = new DisNode(toNode, distanceMap.get(toNode));
                    if (!set.contains(newNode)) {
                        minHeap.push(newNode);
                    }
                    disNodeMap.put(toNode, newNode);
                } else {
                    DisNode curNode = disNodeMap.get(toNode);
                    int minDistance = Math.min(distanceMap.get(toNode), distance + cur.weight);
                    distanceMap.put(toNode, minDistance);
                    minHeap.set(curNode, minDistance);
                }
            }
        }
        return distanceMap;
    }

    //测试
    public static void main(String[] args) {
        Graph graph = new Graph();
        Node A = new Node(1);
        Node B = new Node(2);
        Node C = new Node(3);
        Node D = new Node(4);
        Edge edge1 = new Edge(9, A, D);
        Edge edge2 = new Edge(3, A, B);
        Edge edge3 = new Edge(1, A, C);
        Edge edge4 = new Edge(5, C, B);
        Edge edge5 = new Edge(2, C, D);
        A.edges.add(edge1);
        A.edges.add(edge2);
        A.edges.add(edge3);
        C.edges.add(edge4);
        C.edges.add(edge5);
        HashMap<Node, Integer> distanceMap = dijkstra(A);
        System.out.println(distanceMap.get(C));
    }
}
