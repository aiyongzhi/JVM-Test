package 拓扑排序;

import GrapTemplate.Graph;
import GrapTemplate.Node;

import java.util.*;

//请传参的图是有向无环图
public class SortedTopology {
    public static List<Node> sortedTopology(Graph graph) {
        List<Node> result = new ArrayList<>();
        //之所以要用哈希表存入度是因为，我们不能在图上直接修改入度，不能进行拓扑排序后
        //就破坏了图原本的结构
        HashMap<Node, Integer> inMap = new HashMap<>();//某个节点的入度是多少
        Queue<Node> zeroInQueue = new LinkedList<>();//存入度为0的点
        for (Node cur : graph.nodes.values()) {
            inMap.put(cur, cur.in);
            if (cur.in == 0) {
                zeroInQueue.add(cur);
            }
        }
        //每次从队列中弹出一个点
        while (!zeroInQueue.isEmpty()) {
            Node cur = zeroInQueue.poll();
            result.add(cur);
            for (Node next : cur.nexts) {
                inMap.put(cur, inMap.get(cur) - 1);
                if (inMap.get(cur) == 0) {
                    zeroInQueue.add(cur);
                }
            }
        }
        return result;
    }
}
