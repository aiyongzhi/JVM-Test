import java.util.List;

public class 派对的最大快乐值问题 {
    //给你一颗多叉树表示一个公司员工的上下级关系
    //现在公司要开派对，你可以选择是否给某个员工发请帖
    //但有以下要求，一个员工来参加派对，他的直接上下级不能来参加派对
    public static class Employee {
        public int happy;
        public List<Employee> nexts;

        public Employee(int h, List<Employee> n) {
            happy = h;
            nexts = n;
        }
    }

    public static class Info {
        public int no;//头节点不来的时候获得的最大快来值
        public int yes;//头节点在不来的时候获得的最大快乐值

        public Info(int a, int b) {
            no = a;
            yes = b;
        }
    }

    public static int maxHappy(Employee boss) {
        if (boss == null) {
            return 0;
        }
        Info bossInfo = process(boss);
        return Math.max(bossInfo.yes, bossInfo.no);
    }

    public static Info process(Employee node) {
        if (node == null) {
            return new Info(0, 0);
        }
        int yes = node.happy;
        int no = 0;
        for (Employee employee : node.nexts) {
            Info nextInfo = process(employee);
            yes += nextInfo.no;
            no += Math.max(nextInfo.yes, nextInfo.no);
        }
        return new Info(no, yes);
    }
}
