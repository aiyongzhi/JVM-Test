package 单源最短路径;

import GrapTemplate.Edge;
import GrapTemplate.Node;

import java.util.HashMap;
import java.util.HashSet;

//有向图，无负权值的边，可以有环
public class Dijkstra {
    //思路：需要给定一个源头，从这个源头出发
    //在map记录中拽出最小值且没有选择过的点
    public static Node getMinDistanceAndNoSelectedNode(HashMap<Node, Integer> distanceMap,
                                                       HashSet<Node> selectedNode) {
        Node minNode = null;
        for (Node cur : distanceMap.keySet()) {
            if (!selectedNode.contains(cur)) {
                minNode = minNode == null ? cur : (distanceMap.get(cur) < distanceMap.get(minNode) ? cur : minNode);
            }
        }
        return minNode;
    }

    public static HashMap<Node, Integer> dijkstra(Node from) {
        //key为from能到的点
        //value是from到这些能到的点的最短距离
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        distanceMap.put(from, 0);
        HashSet<Node> selectedNode = new HashSet<>();
        Node minNode = getMinDistanceAndNoSelectedNode(distanceMap, selectedNode);
        while (minNode != null) {
            int distance = distanceMap.get(minNode);
            for (Edge cur : minNode.edges) {
                Node toNode = cur.to;
                if (!distanceMap.containsKey(toNode)) {
                    distanceMap.put(toNode, distance + cur.weight);
                } else {
                    distanceMap.put(toNode, Math.min(distanceMap.get(toNode),
                            distance + cur.weight));
                }
            }
            selectedNode.add(minNode);
            minNode = getMinDistanceAndNoSelectedNode(distanceMap, selectedNode);
        }
        return distanceMap;
    }
}
