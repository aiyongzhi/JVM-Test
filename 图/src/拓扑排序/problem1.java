package 拓扑排序;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

//题目链接：https://www.lintcode.com/problem/127/
public class problem1 {
    public static class DirectedGraphNode {
        public int label;//每个节点的权值
        public ArrayList<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<>();
        }
    }

    //一种比较巧的解法：以A为头的子图的大小如果比B为头的子图的大小大(可以有重复节点)
    //则A的拓扑序<=B
    public static class Record {
        public DirectedGraphNode node;
        public int nodes;//以node为头的子图的大小

        public Record(DirectedGraphNode node, int nodes) {
            this.node = node;
            this.nodes = nodes;
        }
    }

    public static class MyComparator implements Comparator<Record> {
        public int compare(Record o1, Record o2) {
            return o2.nodes - o1.nodes;
        }
    }

    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        HashMap<DirectedGraphNode, Record> order = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            process(node, order);
        }
        ArrayList<Record> recordArray = new ArrayList<>();
        for (Record r : order.values()) {
            recordArray.add(r);
        }
        recordArray.sort(new MyComparator());
        ArrayList<DirectedGraphNode> result = new ArrayList<>();
        for (Record record : recordArray) {
            result.add(record.node);
        }
        return result;
    }

    //写一个方法：当前来到的节点是cur,请把cur为头的子图的大小信息返回
    //x的点次大于等于y,则x的拓扑序小于等于y
    public static Record process(DirectedGraphNode cur, HashMap<DirectedGraphNode, Record>
            order) {
        //如果已经算过直接从缓存里拿值，没算过再算
        if (order.containsKey(cur)) {
            return order.get(cur);
        }
        //没算过，去算
        int nodes = 0;
        for (DirectedGraphNode next : cur.neighbors) {
            nodes += process(next, order).nodes;
        }
        Record ans = new Record(cur, nodes + 1);
        order.put(cur, ans);
        return ans;
    }

}
