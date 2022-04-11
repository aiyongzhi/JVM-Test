import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class 并查集 {
    //并查集的时间复杂度:当你的调用两个方法的次数达到了样本量的规模时，
    //平均下来单次调用的时间复杂度为O(N)
    public static class Node<V> {
        public V value;

        public Node(V v) {
            value = v;
        }
    }

    public static class UnionFindSet<V> {
        //每个样本所对应的节点
        HashMap<V, Node<V>> nodes;
        //每个节点所对应的父节点
        HashMap<Node<V>, Node<V>> parents;
        //代表节点所代表的集合的大小
        HashMap<Node<V>, Integer> sizeMap;

        public UnionFindSet(List<V> list) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V value : list) {
                Node<V> newNode = new Node<>(value);
                nodes.put(value, newNode);
                parents.put(newNode, newNode);
                sizeMap.put(newNode, 1);
            }
        }

        //重要的思路是：我们用代表节点来代表一个集合
        //写一个方法，找到每一个节点所在的集合的代表节点
        //重要优化点1)并把沿途的所有节点都直接指向代表节点，这样就不需要每次都遍历找代表节点
        //第一次后，其它所有次找代表节点时都可以直接找到
        private Node<V> findFather(Node<V> cur) {
            //path记录沿途的所有节点
            Stack<Node<V>> path = new Stack<>();
            while (cur != parents.get(cur)) {
                path.add(cur);
                cur = parents.get(cur);
            }
            //在return cur之前，让沿途的节点全指向代表节点
            while (!path.isEmpty()) {
                parents.put(path.pop(), cur);
            }
            return cur;
        }

        //查看两个元素是否在同一个集合内部
        public boolean isSameSet(V a, V b) {
            return findFather(nodes.get(a)) == findFather(nodes.get(b));
        }

        //合并两个元素所在的集合
        public void union(V a, V b) {
            Node<V> aHead = findFather(nodes.get(a));
            Node<V> bHead = findFather(nodes.get(b));
            if (aHead != bHead) {
                int aSetSize = sizeMap.get(aHead);
                int bSetSize = sizeMap.get(bHead);
                Node<V> big = aSetSize >= bSetSize ? aHead : bHead;
                Node<V> small = big == aHead ? bHead : aHead;
                parents.put(small, big);
                sizeMap.put(big, aSetSize + bSetSize);
                sizeMap.remove(small);
            }
        }
    }
}
