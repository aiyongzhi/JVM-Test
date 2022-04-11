package 拓扑排序;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class problem2 {
    public static class DirectedGraphNode {
        public int label;//每个节点的权值
        public ArrayList<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<>();
        }
    }

    //方法一的拓展
    //x的深度大于等于y的时候，x的拓扑序小于等于y
    public static class Record {
        public DirectedGraphNode node;
        public int deep;

        public Record(DirectedGraphNode node, int deep) {
            this.node = node;
            this.deep = deep;
        }
    }

    public static class MyComparator implements Comparator<Record> {
        public int compare(Record o1, Record o2) {
            return o2.deep - o1.deep;
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

    public static Record process(DirectedGraphNode cur, HashMap<DirectedGraphNode, Record>
            order) {
        if (order.containsKey(cur)) {
            return order.get(cur);
        }
        int deep = 0;
        for (DirectedGraphNode next : cur.neighbors) {
            deep = Math.max(deep, process(next, order).deep);
        }
        Record ans = new Record(cur, deep);
        order.put(cur, ans);
        return ans;
    }
}
