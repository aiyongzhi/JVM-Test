package GrapTemplate;

import java.util.HashMap;
import java.util.HashSet;

//图是由点集和边集构成
public class Graph {
    public HashMap<Integer, Node> nodes;//点集
    public HashSet<Edge> edges;//边集

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
