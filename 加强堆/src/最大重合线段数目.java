import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class 最大重合线段数目 {
    //给你N条线段，每一条线段都有开始位置和结束位置
    //二维数组lines[][]表示N条线段,lines[i][0]表示第i条线段的起点
    //lines[i][1]:表示第i条线段的终点
    //请问，数轴上最多有多少条线段重合
    public static int maxCover1(int[][] lines) {
        //暴力方法
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < lines.length; i++) {
            max = Math.max(max, lines[i][1]);
            min = Math.min(min, lines[i][0]);
        }
        //在遍历最小值到最大值的每一个点5，求max
        int maxCover = 0;
        for (double i = min + 0.5; i < max; i++) {
            int cur = 0;
            for (int j = 0; j < lines.length; j++) {
                if (lines[j][0] < i && lines[j][1] > i) {
                    cur++;
                }
            }
            maxCover = Math.max(maxCover, cur);
        }
        return maxCover;
    }

    //最优解
    //1:将所有线段按，起点位置排好序
    //2:以每一个起点作为重合区域的左边界的开始位点
    //3:求出所有解中的max
    public static class Line {
        public int start;
        public int end;

        public Line(int s, int e) {
            start = s;
            end = e;
        }
    }

    public static class startComparator implements Comparator<Line> {
        public int compare(Line o1, Line o2) {
            return o1.start - o2.start;
        }
    }

    public static int maxCover2(int[][] m) {
        if (m == null || m.length == 0) {
            return 0;
        }
        //1:生成线段数组
        int N = m.length;
        Line[] lines = new Line[N];
        for (int i = 0; i < N; i++) {
            Line line = new Line(m[i][0], m[i][1]);
            lines[i] = line;
        }
        //2:将所有线段按开始位置进行排序
        Arrays.sort(lines, new startComparator());
        //3:准备一个小根堆开始统计
        int maxCover = 0;
        PriorityQueue<Integer> endMinHeap = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            while (!endMinHeap.isEmpty() && endMinHeap.peek() <= lines[i].start) {
                endMinHeap.poll();
            }
            endMinHeap.add(lines[i].end);
            maxCover = Math.max(maxCover, endMinHeap.size());
        }
        return maxCover;
    }

    public static int[][] generateRandomArray(int maxValue, int maxLen) {
        int len = (int) (Math.random() * maxLen) + 1;
        int[][] m = new int[len][2];
        for (int i = 0; i < len; i++) {
            int start = 0;
            int end = 0;
            do {
                start = (int) (Math.random() * maxValue) - (int) (Math.random() * maxValue);
                end = (int) (Math.random() * maxValue) - (int) (Math.random() * maxValue);
            } while (start >= end);
            m[i][0] = start;
            m[i][1] = end;
        }
        return m;
    }

    //写一个数组的拷贝
    public static int[][] copyArray(int[][] m) {
        int N = m.length;
        int M = m[0].length;
        int[][] res = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                res[i][j] = m[i][j];
            }
        }
        return res;
    }

    public static void printArray(int[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        //写一个对数器
        int maxValue = 1000;
        int maxLen = 100;
        int testTime = 100000;
        boolean success = true;
        for (int i = 0; i < testTime; i++) {
            int[][] m1 = generateRandomArray(maxValue, maxLen);
            int[][] m2 = copyArray(m1);
            int max1 = maxCover1(m1);
            int max2 = maxCover2(m2);
            if (max1 != max2) {
                success = false;
                printArray(m1);
                System.out.println("==================");
                printArray(m2);
                System.out.println("==================");
                System.out.println(max1 + "  " + max2);
                break;
            }
        }
        System.out.println(success ? "Nice!" : "Fucking fucked!");
    }
}
