public class 打印折痕 {
    //算法系统班第12节 2小时20分
    public static void printAllFolds(int i, int N, boolean down) {
        //base case
        if (i > N) {
            return;
        }
        printAllFolds(i + 1, N, true);
        System.out.println(down ? "凹" : "凸");
        printAllFolds(i + 1, N, false);
    }

    public static void printAllFolds(int N) {
        printAllFolds(1, N, true);
    }

    public static void main(String[] args) {
        printAllFolds(3);
    }
}
