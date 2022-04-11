import java.util.ArrayList;

public class 找到链表的中间节点 {
    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    //笔试时的借助容器法：借助索引通过下标直接拿到中点
    public static ListNode getMidNode1(ListNode head) {
        if (head == null) {
            return null;
        }
        ArrayList<ListNode> help = new ArrayList<>();
        while (head != null) {
            help.add(head);
            head = head.next;
        }
        return help.get((help.size()) / 2);
    }

    //最优解：快慢指针
    public static ListNode getMidNode2(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    //写一个函数生成给定长度的随机链表
    public static ListNode generateRandomList(int maxLen, int maxValue) {
        ListNode head = new ListNode(0);
        ListNode tail = head;
        int len = (int) (Math.random() * maxLen) + 1;
        while (--len != 0) {
            int value = (int) (Math.random() * maxValue) - (int) (Math.random() * maxValue);
            ListNode newNode = new ListNode(value);
            tail.next = newNode;
            tail = newNode;
        }
        return head;
    }

    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        int maxValue = 100;
        int maxLen = 100;
        int testTime = 100000;
        boolean success = true;
        for (int i = 0; i < testTime; i++) {
            ListNode head = generateRandomList(maxLen, maxValue);
            ListNode midNode1 = getMidNode1(head);
            ListNode midNode2 = getMidNode2(head);
            if (midNode1 != midNode2) {
                success = false;
                printList(head);
                System.out.println("ans1 " + midNode1.val);
                System.out.println("ans2 " + midNode2.val);
                break;
            }
        }
        System.out.println(success ? "Nice!" : "Fucking fucked!");
    }
}
