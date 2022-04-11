package GrapTemplate;

import java.util.ArrayList;

//图的点
public class Node {
    public int val;//点的权值
    public int in;//点的入度
    public int out;//点的出度
    public ArrayList<Node> nexts;//该节点的直接邻居的集合
    public ArrayList<Edge> edges;//以该节点为起点的边的集合

    public Node(int val) {
        this.val = val;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
