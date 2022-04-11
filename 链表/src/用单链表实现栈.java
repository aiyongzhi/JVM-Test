public class 用单链表实现栈 {
    public static class Node<V> {
        public V value;
        public Node<V> next;

        public Node(V val) {
            this.value = val;
        }
    }

    public static class MyStack<V> {
        private Node<V> head;
        private int size;

        public MyStack() {
            head = null;
            size = 0;
        }

        //1：判断是否为空
        public boolean isEmpty() {
            return size == 0;
        }

        //2:得到栈中元素的个数
        public int size() {
            return size;
        }

        //3:向栈中加入元素
        public void push(V value) {
            Node<V> cur = new Node<>(value);
            cur.next = head;
            head = cur;
            size++;
        }

        //4:弹出栈中的元素
        public V pop() {
            V res = null;
            if (head != null) {
                res = head.value;
                head = head.next;
                //JVM会帮我们释放链表的头节点，如果是c/c++需要手动释放
                size--;
            }
            return res;
        }

        //5:拿到栈顶的元素
        public V peek() {
            V res = null;
            if (head != null) {
                res = head.value;
            }
            return res;
        }
    }

    public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack<>();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
