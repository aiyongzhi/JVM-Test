import java.util.Stack;

public class 判断链表是否回文 {
    //给定一个链表的头节点head,请判断该链表是否是回文结构
    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    //用容器的方法储存当前链表的逆序，再逐一比对
    public static boolean isPalindrome1(ListNode head) {
        //默认空链表是回文结构
        if (head == null) {
            return true;
        }
        Stack<ListNode> stack = new Stack<>();
        ListNode cur = head;
        while (cur != null) {
            stack.add(cur);
            cur = cur.next;
        }
        //栈中储存的是当前链表的逆序
        //逐一弹出和原链表对比，如果值全都一样则是回文
        //只要有一个不一样就不是回文
        cur = head;
        while (!stack.isEmpty()) {
            if (stack.pop().val != cur.val) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }
    //不用容器的方法:面试时候的方法
    //1:找到中点，将中点开始的右半部分逆序
    //2:向数组回文一样，定义左右指针同时向中间走

    //逆序链表返回新的头
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

    public static boolean isPalindrome2(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        //slow定位到了链表的中点
        ListNode L = head;
        ListNode R = reverse(slow);
        ListNode tail = R;
        while (L != null && R != null) {
            if (L.val != R.val) {
                return false;
            }
            L = L.next;
            R = R.next;
        }
        reverse(tail);
        return true;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        System.out.println(isPalindrome1(head));
        System.out.println(isPalindrome2(head));
    }
}
