public class 用双向链表实现双端队列 {
    public static class DoubleNode<V> {
        public V value;
        public DoubleNode<V> next;
        public DoubleNode<V> last;

        public DoubleNode(V value) {
            this.value = value;
            next = null;
            last = null;
        }
    }

    //双端队列可以完成，从头部加入元素，从头部删除元素
    //从尾部加入元素，从尾部删除元素
    public static class MyDeque<V> {
        private DoubleNode<V> head;
        private DoubleNode<V> tail;
        private int size;

        public MyDeque() {
            head = null;
            tail = null;
            size = 0;
        }

        //1:判断双端队列是否为空
        public boolean isEmpty() {
            return size == 0;
        }

        //2:双端队列的大小
        public int size() {
            return this.size;
        }

        //3:从双端队列头部加入元素
        //第一加入元素的时候，头指针和尾指针都要指向这个节点
        //之和每次加入，只需要改变头，不需要改变尾节点，因此要分类讨论
        public void pushHead(V value) {
            DoubleNode<V> cur = new DoubleNode<>(value);
            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                cur.next = head;
                head.last = cur;
                head = cur;
            }
            size++;
        }

        //从双端队列的尾部加入元素(相当于链表的尾插)
        //第一次加入的时候，头指针和尾指针均要指向该节点
        //之后的每次加入，只需要改变尾部节点即可
        public void pushTail(V value) {
            DoubleNode<V> cur = new DoubleNode<>(value);
            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                cur.last = tail;
                tail.next = cur;
                tail = cur;
            }
            size++;
        }

        //从头部弹出数据
        //如果只有一个节点时，弹出后头指针和尾指针都需要置为null
        //如果不只有1个节点，弹出后只需要改变头节点
        public V pollHead() {
            V res = null;
            //如果链表为空，直接返回
            if (head == null) {
                return res;
            }
            //链表不为空
            res = head.value;
            size--;
            //如果只有一个节点时
            if (head == tail) {
                head = null;
                tail = null;
            } else {//不只有一个节点时
                head = head.next;
                head.last = null;
            }
            return res;
        }

        //从尾部弹出数据
        //如果链表为空，直接返回null
        //如果链表只有一个元素，弹出后，头指针和尾指针均置空
        public V pollTail() {
            V res = null;
            //链表为空
            if (head == null) {
                return null;
            }
            //链表不为空
            res = tail.value;
            size--;
            //如果只有一个节点
            if (head == tail) {
                head = null;
                tail = null;
            } else {//链表不只有一个节点
                tail = tail.last;
                tail.next = null;
            }
            return res;
        }
    }
}
