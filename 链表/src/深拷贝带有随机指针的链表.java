import java.util.HashMap;

public class 深拷贝带有随机指针的链表 {
    //力扣第138题
    public class Node {
        public int val;
        public Node next;
        public Node random;

        public Node(int val) {
            this.val = val;
        }
    }

    //笔试的时候用的方法，带容器的方法
    public Node copyRandomList1(Node head) {
        if (head == null) {
            return null;
        }
        //用哈希表简单，笔试的时候用的
        HashMap<Node, Node> nodeToNodeMap = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            nodeToNodeMap.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        //把随机指针加上
        cur = head;
        while (cur != null) {
            //设置好next指针
            nodeToNodeMap.get(cur).next = nodeToNodeMap.get(cur.next);
            //设置好random指针
            nodeToNodeMap.get(cur).random = nodeToNodeMap.get(cur.random);
            cur = cur.next;
        }
        return nodeToNodeMap.get(head);
    }

    //面试的时候用的，不带任何容器的方法
    public Node copyRandomList2(Node head) {
        if (head == null) {
            return null;
        }
        //在原链表每个节点后面都拷贝一样的节点
        Node cur = head;
        while (cur != null) {
            Node newNode = new Node(cur.val);
            newNode.next = cur.next;
            cur.next = newNode;
            cur = newNode.next;
        }
        //把随机指针加上
        cur = head;
        while (cur != null) {
            cur.next.random = cur.random == null ? null : cur.random.next;
            cur = cur.next.next;
        }
        //再把原链表和深拷贝的链表断开
        Node newHead = head.next;
        Node prev = head;
        cur = head.next;
        Node next = null;
        while (prev != null) {
            next = cur.next;
            prev.next = next;
            cur.next = next != null ? next.next : null;
            prev = next;
            cur = next != null ? next.next : null;
        }
        return newHead;
    }
}
