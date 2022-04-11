public class 链表的反转 {
    //请对给定的以head为头的链表进行反转

    //单链表的反转
    public static class Node {
        public int value;
        public Node next;

        public Node(int val) {
            this.value = val;
        }
    }

    //将单链表反转后并返回新的头
    public static Node reverseList(Node head) {
        Node prev = null;
        Node next = null;
        Node cur = head;
        //三步走
        //1:要处理当前节点之前，要先记录下一个节点的引用
        //2:处理当前节点cur.next=prev
        //3:移动指针，去处理下一个节点
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    //将双向链表反转，并返回新的头
    public static class doubleNode {
        public int value;
        public doubleNode next;//指向下一个节点的指针
        public doubleNode last;//指向上一个节点的指针

        public doubleNode(int val) {
            this.value = val;
        }
    }

    public static doubleNode reverseDoubleList(doubleNode head) {
        doubleNode prev = null;
        doubleNode cur = head;
        //和上述单链表的逆序相似，双向链表的逆序也有三步
        //但双向链表有指向下一个节点的指针也有指向上一个节点的指针，因此不需要next引用
        //1:处理好当前节点的last,next指针
        //2:prev来到当前节点
        //3:cur来到下一个节点继续处理
        while (cur != null) {
            cur.last = cur.next;
            cur.next = prev;
            prev = cur;
            cur = cur.last;
        }
        return prev;
    }
}
