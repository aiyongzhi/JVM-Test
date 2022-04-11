public class 链表的相加 {
    //给定两个链表，代表两个正数
    //每个链表从头开始向后数，代表数字由低位向高位走
    //例如：3->4->6->1
    //     7->9->7
    //   ==0->4->4->2
    //==1643+797=2440
    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    //相加之后，返回相加链表的头部
    public static Node addTwoNumber(Node head1, Node head2) {
        //如果其中一个为空，无法相加
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }
        //head1和head2均不为空
        Node curA = head1;
        Node curB = head2;
        Node newHead = null;
        Node newTail = null;

        int digit = 0;//储存当前位应该放什么
        int tmp = 0;//储存进位
        while (curA != null || curB != null) {
            int sum = (curA == null ? 0 : curA.value) + (curB == null ? 0 : curB.value) + tmp;
            digit = sum % 10;
            tmp = sum / 10;
            Node newNode = new Node(digit);
            //讲新节点尾插入链表中
            if (newHead == null) {
                newHead = newNode;
                newTail = newNode;
            } else {
                newTail.next = newNode;
                newTail = newNode;
            }
            //移动curA和curB
            curA = curA != null ? curA.next : curA;
            curB = curB != null ? curB.next : curB;
        }
        //查看最后的进位数字,将他加入链表中去
        if (tmp == 1) {
            Node newNode = new Node(1);
            newTail.next = newNode;
            newTail = newNode;
        }
        return newHead;
    }

    //写一个函数打印一个链表
    public static void disPlay(Node head) {
        while (head != null) {
            System.out.print(head.value + "->");
            head = head.next;
        }
        System.out.print("null");
        System.out.println();
    }

    //测试一下
    public static void main(String[] args) {
        Node head1 = new Node(3);
        head1.next = new Node(4);
        head1.next.next = new Node(6);
        head1.next.next.next = new Node(1);
        Node head2 = new Node(7);
        head2.next = new Node(9);
        head2.next.next = new Node(7);
        Node newHead = addTwoNumber(head1, head2);
        disPlay(newHead);
    }
}
