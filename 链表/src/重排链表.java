import java.util.Stack;

public class 重排链表 {
    //长度为偶数的链表，现有如下规定
    //例如长度为8的链表,L1->L2->L3->L4->R1->R2->R3->R4->null
    //链表重排后:L1->R4->L2->R3->L3->R2->L4->R1->null
    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    //借助容器法：笔试的时候用
    //找到中间节点，将右半部分全部入栈
    public static ListNode f(ListNode head) {
        if (head == null) {
            return null;
        }
        Stack<ListNode> stack = new Stack<>();
        ListNode midNode = findMidListNode(head);
        while (midNode != null) {
            stack.add(midNode);
            midNode = midNode.next;
        }
        ListNode cur = head;
        ListNode next = null;
        while (!stack.isEmpty()) {
            next = cur.next;
            ListNode node = stack.pop();
            cur.next = node;
            node.next = next != node ? next : null;
            cur = next;
        }
        return head;
    }

    public static ListNode findMidListNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public static ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode next = null;
        while (head != null) {
            next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    //面试时候用的方法，不用额外空间
    public static ListNode f2(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode mid = findMidListNode(head);
        ListNode R = reverse(mid);
        ListNode L = head;
        //创建一个哨兵节点
        ListNode preHead = new ListNode(0);
        ListNode cur = preHead;
        while (R != null) {
            ListNode next = L.next;
            cur.next = L;
            L.next = R;
            cur = R;
            L = next;
            R = R.next;
        }
        return preHead.next;
    }

    public static void print(ListNode head) {
        while (head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
        System.out.println("null");
    }

    //1->2->3->4->5->6->null
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        f2(head);
        print(head);
    }
}
