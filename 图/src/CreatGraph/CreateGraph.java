package CreatGraph;

import GrapTemplate.Edge;
import GrapTemplate.Graph;
import GrapTemplate.Node;

//生成一个图
public class CreateGraph {
    //matrix是一个n*3的矩阵
    //[weight...from....to]表是一条边的信息
    //matrix每一行表示一条边
    public static Graph createGraph(int[][] matrix) {
        Graph graph = new Graph();
        for (int i = 0; i < matrix.length; i++) {
            //1:拿到一条边的信息
            Integer weight = matrix[i][0];
            Integer from = matrix[i][1];
            Integer to = matrix[i][2];
            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }
            //2:把边建立好
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge newEdge = new Edge(weight, fromNode, toNode);
            graph.edges.add(newEdge);
            //3:把起点和终点的信息设置好
            fromNode.out++;
            fromNode.nexts.add(toNode);
            fromNode.edges.add(newEdge);
            toNode.in++;
            graph.edges.add(newEdge);
        }
        return graph;
    }

}
