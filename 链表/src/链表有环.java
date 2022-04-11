import java.util.HashSet;

public class 链表有环 {
    //如果链表有环则返回链表入环的第一个节点，无环则返回null
    public static class Node {
        public int val;
        public Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    //笔试的时候，用容器
    //时间复杂度O(N),空间复杂度O(N)
    public static Node isRing1(Node head) {
        if (head == null) {
            return null;
        }
        HashSet<Node> set = new HashSet<>();
        Node cur = head;
        while (cur != null) {
            if (set.contains(cur)) {
                return cur;
            }
            set.add(cur);
            cur = cur.next;
        }
        return null;
    }

    //面试时候，不用容器
    public static Node isRing2(Node head) {
        if (head == null) {
            return null;
        }
        Node fast = head;
        Node slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        if (fast == null || fast.next == null) {
            return null;
        }
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}
