package 单源最短路径;

import GrapTemplate.Node;

public class DisNode {
    //包装一个节点和距离的类方便用加强堆{
    public Node node;
    public int distance;

    public DisNode(Node node, int distance) {
        this.node = node;
        this.distance = distance;
    }
}
