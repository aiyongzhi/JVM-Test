public class K个一组翻转链表 {
    //题目K个一组翻转链表
    //力扣第25题
    //写一个方法：拿到一组的结尾
    public class ListNode {
        public int value;
        public ListNode next;

        public ListNode(int value) {
            this.value = value;
        }
    }

    public ListNode getKGroupEnd(ListNode start, int k) {
        while (--k != 0 && start != null) {
            start = start.next;
        }
        return start;
    }

    //写一个方法：逆序每一组的节点
    public void reverse(ListNode start, ListNode end) {
        end = end.next;
        ListNode prev = null;
        ListNode cur = start;
        ListNode next = null;
        while (cur != end) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        start.next = end;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode start = head;
        ListNode end = getKGroupEnd(start, k);
        if (end == null) {
            return head;
        }
        reverse(start, end);
        head = end;
        ListNode lastEnd = start;
        while (lastEnd.next != null) {
            start = lastEnd.next;
            end = getKGroupEnd(start, k);
            if (end == null) {
                return head;
            }
            reverse(start, end);
            lastEnd.next = end;
            lastEnd = start;
        }
        return head;
    }
}
