
public class 汉诺塔问题 {
    //汉诺塔问题
    //Swap方法将一个盘子从from柱子上移动到to柱子上
    public static void Swap(String from, String to) {
        System.out.println(from + "->" + to);
    }

    //递归含义：将n层汉诺塔从from柱子移动到to柱子上
    public static void process(int n, String from, String to, String help) {
        //base case
        if (n == 1) {
            Swap(from, to);
        } else {
            process(n - 1, from, help, to);
            Swap(from, to);
            process(n - 1, help, to, from);
        }
    }

    public static void hanno(int n) {
        if (n < 0) {
            return;
        }
        process(n, "A", "C", "B");
    }

    public static void main(String[] args) {
        hanno(3);
    }
}
