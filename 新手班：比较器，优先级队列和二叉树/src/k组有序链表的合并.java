//力扣原题

import java.util.Comparator;
import java.util.PriorityQueue;

public class k组有序链表的合并 {
    public class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int a) {
            val = a;
        }
    }

    public class minComparator implements Comparator<ListNode> {
        public int compare(ListNode o1, ListNode o2) {
            return o1.val - o2.val;
        }
    }

    public ListNode merge(ListNode[] lists) {
        //准备一个小根堆做辅助
        PriorityQueue<ListNode> minQueue = new PriorityQueue<>(new minComparator());
        //1:初始化小根堆，让k个链表的头全进去
        for (int i = 0; i < lists.length; i++) {
            ListNode node = lists[i];
            if (node != null) {
                minQueue.add(node);
            }
        }
        //2:第一次从小根堆中弹出元素时，需要改变头节点的数值
        ListNode head = minQueue.poll();
        if (head.next != null) {
            minQueue.add(head.next);
        }
        ListNode cur = head;
        //小根堆规则：
        //每次从堆中弹出一个元素，链接到总链表上面
        //再把弹出元素的下一个加入队列中去
        while (!minQueue.isEmpty()) {
            cur.next = minQueue.poll();
            cur = cur.next;
            if (cur.next != null) {
                minQueue.add(cur.next);
            }
        }
        return head;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        ListNode head = merge(lists);
        return head;
    }
}
