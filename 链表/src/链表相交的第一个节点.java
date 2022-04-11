public class 链表相交的第一个节点 {
    //给定两个单链表的头节点Head1,Head2,这两个链表可能有环也可能无环，如果两个链表相交，则返回
    //相交的第一个节点，若两个链表不相交，则返回null
    public static class Node {
        public int val;
        public Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    //链表相交的第一个节点
    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loop1 = getLoop(head1);
        Node loop2 = getLoop(head2);
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        } else if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);
        }
        return null;
    }

    //拿到链表入环的第一个节点
    public static Node getLoop(Node head) {
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

    //如果两个链表都没有环，返回其相交的第一个节点
    public static Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;
        //1:统计长短链表的差值
        while (cur1.next != null) {
            n++;
            cur1 = cur1.next;
        }
        while (cur2.next != null) {
            n--;
            cur2 = cur2.next;
        }
        if (cur1 != cur2) {
            return null;
        }
        //两个链表一定相交
        //2:长链表走差值步
        cur1 = n > 0 ? head1 : head2;//长链表
        cur2 = cur1 == head1 ? head2 : head1;//短链表
        n = n > 0 ? n : -n;
        while (n-- != 0) {
            cur1 = cur1.next;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    //如果两个链表都有环，求相交的第一个节点
    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        Node cur1 = head1;
        Node cur2 = head2;
        if (loop1 == loop2) {
            int n = 0;
            while (cur1 != loop1) {
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2) {
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = n > 0 ? n : -n;
            while (n-- != 0) {
                cur1 = cur1.next;
            }
            //一起走
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {
            cur1 = loop1.next;
            while (cur1 != loop1) {
                if (cur1 == loop2) {
                    return loop1;
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }
}
