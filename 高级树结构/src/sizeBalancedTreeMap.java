import java.security.Key;

public class sizeBalancedTreeMap {
    //有序表，每个节点有key和value，元素按key有序组织
    public static class SBTNode<K extends Comparable<K>, V> {
        public K key;
        public V value;
        public SBTNode<K, V> left;
        public SBTNode<K, V> right;
        public int size;

        public SBTNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    //SB树是由树根root来维护的
    public static class sizeBalanceTreeMap<K extends Comparable<K>, V> {
        private SBTNode<K, V> root;

        //写一个基本操作左旋,右旋
        //返回旋转结束后新的头
        private SBTNode<K, V> leftRotate(SBTNode<K, V> cur) {
            //将以cur为头的子树进行左旋，并返回新的头
            SBTNode<K, V> rightNode = cur.right;
            cur.right = rightNode.left;
            rightNode.left = cur;

            rightNode.size = cur.size;
            cur.size = (cur.left == null ? 0 : cur.size) + (cur.right == null ? 0 : cur.size) + 1;
            return rightNode;
        }

        private SBTNode<K, V> rightRotate(SBTNode<K, V> cur) {
            SBTNode<K, V> leftNode = cur.left;
            cur.left = leftNode.right;
            leftNode.right = cur;

            leftNode.size = cur.size;
            cur.size = (cur.left == null ? 0 : cur.size) + (cur.right == null ? 0 : cur.size) + 1;
            return leftNode;
        }

        private SBTNode<K, V> findLastIndex(K key) {
            SBTNode<K, V> cur = this.root;
            SBTNode<K, V> prev = this.root;
            while (cur != null) {
                prev = cur;
                if (key.compareTo(cur.key) == 0) {
                    break;
                } else if (key.compareTo(cur.key) < 0) {
                    cur = cur.left;
                } else {
                    cur = cur.right;
                }
            }
            return prev;
        }

        //调整操作
        private SBTNode<K, V> matain(SBTNode<K, V> cur) {
            if (cur == null) {
                return null;
            }
            //否则，检验4种达标情况
            if (cur.left != null && cur.left.left != null && cur.right != null && (cur.left.left.size) >
                    cur.right.size) {
                //LL型违规
                cur = rightRotate(cur);
                cur.right = matain(cur.right);
                cur = matain(cur);
            } else if (cur.left != null && cur.left.right != null && cur.right != null &&
                    (cur.left.right.size > cur.right.size)) {
                cur.left = leftRotate(cur.left);
                cur = rightRotate(cur);
                cur.left = matain(cur.left);
                cur.right = matain(cur.right);
                cur = matain(cur);
            } else if (cur.right != null && cur.right.right != null && cur.left != null && cur.right.right.size > cur.left.size) {
                cur = leftRotate(cur);
                cur.left = matain(cur.left);
                cur = matain(cur);
            } else if (cur.right != null && cur.right.left != null && cur.left != null && cur.right.left.size > cur.left.size) {
                cur.right = rightRotate(cur.right);
                cur = leftRotate(cur);
                cur.left = matain(cur.left);
                cur.right = matain(cur.right);
                cur = matain(cur);
            }
            return cur;
        }
        //我们规定：在加入元素的时候，进行平衡调整:调整过后加入新的头

        private SBTNode<K, V> add(SBTNode<K, V> cur, K key, V value) {
            if (cur == null) {
                return new SBTNode<K, V>(key, value);
            }
            cur.size++;//加入了一个节点
            if (key.compareTo(cur.key) < 0) {
                cur.left = add(cur.left, key, value);
            } else {
                cur.right = add(cur.right, key, value);
            }
            return matain(cur);
        }

        public void put(K key, V value) {
            if (key == null) {
                throw new RuntimeException("This key is error:");
            }
            //有两种情况，要么是要更新，要么是添加新的节点
            SBTNode<K, V> lastNode = findLastIndex(key);
            if (lastNode != null && key.compareTo(lastNode.key) == 0) {
                lastNode.value = value;
            } else {
                this.root = add(root, key, value);
            }
        }

        //查找：根据key查value
        public V get(K key) {
            if (key == null) {
                throw new RuntimeException("This key is error:!");
            }
            SBTNode<K, V> lastNode = findLastIndex(key);
            if (lastNode != null && key.compareTo(lastNode.key) == 0) {
                return lastNode.value;
            } else {
                return null;
            }
        }

        //删除节点:可能会删除头，所以要有个返回值
        private SBTNode<K, V> delete(SBTNode<K, V> cur, K key) {
            cur.size--;
            if (key.compareTo(cur.key) > 0) {
                cur.right = delete(cur.right, key);
            } else if (key.compareTo(cur.key) < 0) {
                cur.left = delete(cur.left, key);
            } else {
                //要删除cur，有四种情况
                if (cur.left == null && cur.right == null) {
                    cur = null;
                    //c++要写内存释放
                } else if (cur.left == null && cur.right != null) {
                    cur = cur.right;
                } else if (cur.left != null && cur.right == null) {
                    cur = cur.left;
                } else {
                    //找到右子树的最左节点
                    SBTNode<K, V> deleteNode = cur.right;
                    SBTNode<K, V> prev = null;
                    deleteNode.size--;
                    while (deleteNode.left != null) {
                        prev = cur;
                        deleteNode = deleteNode.left;
                        deleteNode.size--;
                    }
                    if (prev != null) {
                        prev.left = deleteNode.right;
                        deleteNode.right = cur.right;
                    }
                    deleteNode.left = cur.left;
                    //设置size
                    deleteNode.size = deleteNode.left.size + deleteNode.right.size + 1;
                    cur = deleteNode;
                }
            }
            return cur;
        }

    }
}
