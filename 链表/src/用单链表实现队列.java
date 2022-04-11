public class 用单链表实现队列 {
    public static class Node<V> {
        public V value;
        public Node<V> next;

        public Node(V val) {
            this.value = val;
        }
    }

    public static class MyQueue<V> {
        private Node<V> head;
        private Node<V> tail;
        private int size;

        public MyQueue() {
            head = null;
            tail = null;
            size = 0;
        }

        //1:判断队列是否为空
        public boolean isEmpty() {
            return size == 0;
        }

        //2:拿出队列的大小
        public int size() {
            return size;
        }

        //3:队列中添加元素
        public void offer(V value) {
            Node<V> cur = new Node<>(value);
            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                tail.next = cur;
                tail = cur;
            }
            size++;
        }

        //4:弹出元素
        public V poll() {
            V res = null;
            if (head != null) {
                res = head.value;
                head = head.next;
                if (head == null) {
                    tail = null;
                }
                size--;
            }
            return res;
        }

        //5:拿到队头的元素
        public V peek() {
            V res = null;
            if (head != null) {
                res = head.value;
            }
            return res;
        }
    }

    public static void main(String[] args) {
        MyQueue<Integer> myQueue = new MyQueue<>();
        for (int i = 0; i < 10; i++) {
            myQueue.offer(i);
        }
        while (!myQueue.isEmpty()) {
            System.out.println(myQueue.poll());
        }
    }
}
