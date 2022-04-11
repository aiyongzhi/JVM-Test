import java.util.ArrayList;
import java.util.Arrays;

public class 跳表 {
    //跳表的增删改查
    //跳表的时间复杂度为O(logN)  类似于由顶层对下层进行二分
    public static class SkipListNode<K extends Comparable<K>, V> {
        public K key;
        public V value;
        public ArrayList<SkipListNode<K, V>> nextNodes;

        public SkipListNode(K key, V value) {
            nextNodes = new ArrayList<SkipListNode<K, V>>();
        }

        //封装一个判断节点的key是否相同的方法
        public boolean isKeyEqual(K otherKey) {
            //当前节点的key与另一个key比较
            return (this.key == null && otherKey == null) ||
                    (this.key != null && otherKey != null && this.key.compareTo(otherKey) == 0);
        }

        //判断当前节点是否小于otherKey
        public boolean isKeyLess(K otherKey) {
            return otherKey != null && (this.key.compareTo(otherKey) < 0);
        }
    }

    //封装一些基础的方法
    public static class SkipListMap<K extends Comparable<K>, V> {
        private static final double PROBABILITY = 0.5;
        private SkipListNode<K, V> head;
        private int size;
        private int maxLevel;

        public SkipListMap() {
            head = new SkipListNode<K, V>(null, null);
            head.nextNodes.add(null);
            size = 0;
            maxLevel = 0;
        }

        //写一个方法，获得每一层小于key的最右节点
        private SkipListNode<K, V> mostRightLessNodeInLevel(K key, SkipListNode<K, V> cur, int level) {
            SkipListNode<K, V> next = cur.nextNodes.get(level);
            while (next != null && next.isKeyLess(key)) {
                cur = next;
                next = next.nextNodes.get(level);
            }
            return cur;
        }

        //写一个方法：从最高层开始检索，拿到第0层小于key的最右节点，也就是所有节点小于key最大的节点
        private SkipListNode<K, V> mostRightLessNodeInTree(K key) {
            //base case
            if (key == null) {
                return null;
            }
            int level = this.maxLevel;
            SkipListNode<K, V> cur = this.head;
            while (level >= 0) {
                cur = mostRightLessNodeInLevel(key, cur, level--);
            }
            return cur;
        }

        //查找跳表中是否存在key
        public boolean containsKey(K key) {
            //如果key是空，我们认为没有找到
            if (key == null) {
                return false;
            }
            //只有第0层没找到key，才能断言表中没有key
            SkipListNode<K, V> rightLessKey = mostRightLessNodeInTree(key);
            return rightLessKey != null && (rightLessKey.nextNodes.get(0).isKeyEqual(key));
        }

        //在表中添加元素
        public void put(K key, V value) {
            //base case
            if (key == null) {
                return;
            }
            //有两种情况：
            //一：更新key所对应的value
            //二：添加新的节点
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> find = less.nextNodes.get(0);
            if (find != null && find.isKeyEqual(key)) {
                find.value = value;
            } else {
                //添加元素
                //1:size++
                this.size++;
                SkipListNode<K, V> newNode = new SkipListNode<>(key, value);
                //2:随机出newNode的层数
                int newNodeLevel = 0;
                while (Math.random() < PROBABILITY) {
                    newNodeLevel++;
                }
                //3:查看newNodeLevel和maxLevel的大小关系，判断是否要扩容
                while (newNodeLevel > maxLevel) {
                    head.nextNodes.add(null);
                    maxLevel++;
                }
                //4:先给newNode层数，先占位
                for (int i = 0; i < newNodeLevel; i++) {
                    newNode.nextNodes.add(null);
                }
                //5:开始添加操作，从最高层开始往下层添加
                //三步走：1:每一层找到<key的最右节点
                //2:如果这一层被newNodeLevel拥有则添加，否则直接下一层
                int level = this.maxLevel;
                SkipListNode<K, V> prev = head;
                while (level >= 0) {
                    prev = mostRightLessNodeInLevel(key, prev, level);
                    if (newNodeLevel <= level) {
                        newNode.nextNodes.set(level, prev.nextNodes.get(level));
                        prev.nextNodes.set(level, newNode);
                    }
                    level--;
                }
            }
        }

        //下一个方法：根据key拿到value
        public V get(K key) {
            if (key == null) {
                return null;
            }
            //只有最后一层说没找到，才是没找到
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            return less != null && (less.nextNodes.get(0).isKeyEqual(key)) ? less.nextNodes.get(0).value : null;
        }

        //删除节点
        public void remove(K key) {
            //要删除，必须保证表中有该节点
            if (containsKey(key)) {
                size--;
                int level = maxLevel;
                SkipListNode<K, V> prev = head;
                while (level >= 0) {
                    //1:每层找到<k的最右节点
                    prev = mostRightLessNodeInLevel(key, prev, level);
                    SkipListNode<K, V> next = prev.nextNodes.get(level);
                    //要删则删除
                    if (next != null && next.isKeyEqual(key)) {
                        prev.nextNodes.set(level, next.nextNodes.get(level));
                    }
                    //2:如果当前层没有节点则进行删除层操作
                    //不要把第0层给删除了
                    if (level != 0 && prev == head && prev.nextNodes.get(level) == null) {
                        head.nextNodes.remove(level);
                        maxLevel--;
                    }
                    level--;
                }
            }
        }
    }
}
