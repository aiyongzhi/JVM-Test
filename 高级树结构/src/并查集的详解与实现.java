import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class 并查集的详解与实现 {
    //难度不是很难的算法
    //时间复杂度为O(1) 合并和查找的时间复杂度
    public static class Element<V> {
        public V value;

        public Element(V value) {
            this.value = value;
        }
    }

    public static class UnionFindSet<V> {
        //并查集由节点集
        //父亲集
        public HashMap<V, Element<V>> elementMap;
        public HashMap<Element<V>, Element<V>> fatherMap;
        //这个sizeMap存的是由代表节点所代表的集合的空间大小
        public HashMap<Element<V>, Integer> sizeMap;

        public UnionFindSet(List<V> list) {
            elementMap = new HashMap<>();
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
            //利用数据集合，初始化这三张表
            for (V value : list) {
                Element<V> element = new Element<>(value);
                elementMap.put(value, element);
                fatherMap.put(element, element);
                sizeMap.put(element, 1);
            }
        }

        //写一个重要的方法，找到任意节点的代表节点
        //无论是判断两个元素是否属于一个集合,
        //还是合并两个元素所在的集合，都需要找到元素的代表节点\
        private Element<V> findHead(Element<V> element) {
            //为了加速，在不断向上找代表节点的同时，将沿途路径上的所有节点全部入栈
            //在出栈时让所有节点全部指向这个代表节点，这样可以高效率的加速
            Stack<Element<V>> stack = new Stack<>();
            while (element != fatherMap.get(element)) {
                stack.push(element);
                element = fatherMap.get(element);
            }
            //element停在代表节点处
            //出栈
            while (!stack.isEmpty()) {
                fatherMap.put(stack.pop(), element);
            }
            return element;
        }

        //并查集最重要的两个操作：
        //1:判断两个元素是否在同一个集合
        //2:合并两个集合
        public boolean isSameSet(V a, V b) {
            if (elementMap.containsKey(a) && elementMap.containsKey(b)) {
                return findHead(elementMap.get(a)) == findHead(elementMap.get(b));
            }
            //没有加入过的元素，一律认为不在同一个集合
            return false;
        }

        public void union(V a, V b) {
            if (elementMap.containsKey(a) && elementMap.containsKey(b)) {
                Element<V> headA = findHead(elementMap.get(a));
                Element<V> headB = findHead(elementMap.get(b));
                if (headA != headB) {
                    Element<V> big = sizeMap.get(headA) > sizeMap.get(headB) ? headA : headB;
                    Element<V> smale = big == headA ? headB : headA;
                    fatherMap.put(smale, big);
                    sizeMap.put(big, sizeMap.get(headA) + sizeMap.get(headB));
                    sizeMap.remove(smale);
                }
            }
        }
    }
}
