public class 链表的分区 {
    //请将单向链表按照划分值进行划分为，左边小中间相等右边大的形式
    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode partition(ListNode head, int k) {
        ListNode sH = null;
        ListNode sT = null;
        ListNode eH = null;
        ListNode eT = null;
        ListNode mH = null;
        ListNode mT = null;
        ListNode cur = head;
        ListNode next = null;
        //1:先将链表分为三个子链表
        while (cur != null) {
            next = cur.next;
            if (cur.val < k) {
                if (sH == null) {
                    sH = cur;
                    sT = cur;
                } else {
                    sT.next = cur;
                    sT = cur;
                }
            } else if (cur.val == k) {
                if (eH == null) {
                    eH = cur;
                    eT = cur;
                } else {
                    eT.next = cur;
                    eT = cur;
                }
            } else {
                if (mH == null) {
                    mH = cur;
                    mT = cur;
                } else {
                    mT.next = cur;
                    mT = cur;
                }
            }
            cur.next = null;
            cur = next;
        }
        //2:将三个区域的链表链接起来
        if (sH != null) {
            sT.next = eH != null ? eH : mH;
        }
        if (eH != null) {
            eT.next = mH;
        }
        //3:处理好新的头
        return sH != null ? sH : (eH != null ? eH : mH);
    }

    public static void print(ListNode head) {
        while (head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
        System.out.println("null");
    }

    //3->7->8->2->5->3->9->null
    public static void main(String[] args) {
        int k = 4;
        ListNode head = new ListNode(3);
        head.next = new ListNode(7);
        head.next.next = new ListNode(8);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(3);
        head.next.next.next.next.next.next = new ListNode(9);
        head = partition(head, k);
        print(head);
    }
}
