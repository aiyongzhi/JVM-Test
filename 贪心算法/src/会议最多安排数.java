import java.util.*;

public class 会议最多安排数 {
    public static class Program {
        public int start;
        public int end;

        public Program(int a, int b) {
            start = a;
            end = b;
        }
    }

    public static class endComparator implements Comparator<Program> {
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }
    }

    //请保证所有的会议结束时间晚于开始时间
    public static int bestArrange1(int[][] m) {
        if (m == null || m.length == 0) {
            return 0;
        }
        Program[] programs = new Program[m.length];
        for (int i = 0; i < m.length; i++) {
            programs[i] = new Program(m[i][0], m[i][1]);
        }
        Arrays.sort(programs, new endComparator());
        int ans = 0;
        int end = 0;
        for (int i = 0; i < programs.length; i++) {
            if (programs[i].start >= end) {
                end = programs[i].end;
                ans++;
            }
        }
        return ans;
    }

    //暴力方法
    //数组的全排列
    public static int bestArrange2(int[][] m) {
        if (m == null || m.length == 0) {
            return 0;
        }
        Program[] programs = new Program[m.length];
        for (int i = 0; i < m.length; i++) {
            programs[i] = new Program(m[i][0], m[i][1]);
        }
        ArrayList<Program> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, programs);
        return process(arrayList, 0, 0);
    }

    //写一个递归:剩下还可以安排的会议全在programs里面，现在已经安排了done件会议,
    //现在来到的时间点是timeLimit
    public static int process(ArrayList<Program> programs, int done, int timeLimit) {
        if (programs.size() == 0) {
            return done;
        }
        //还有会议可以安排
        int max = done;
        for (int i = 0; i < programs.size(); i++) {
            if (timeLimit <= programs.get(i).start) {
                ArrayList<Program> next = new ArrayList<>(programs);
                next.remove(programs.get(i));
                max = Math.max(max, process(next, done + 1, programs.get(i).end));
            }
        }
        return max;
    }

    public static int[][] generateArray(int maxLen, int maxValue) {
        int len = (int) (Math.random() * maxLen) + 1;
        int[][] res = new int[len][2];
        for (int i = 0; i < len; i++) {
            int r1 = (int) (Math.random() * maxValue) + 1;
            int r2 = (int) (Math.random() * maxValue) + 1;
            if (r1 == r2) {
                res[i][0] = r1;
                res[i][1] = r1 + 1;
            } else {
                res[i][0] = Math.min(r1, r2);
                res[i][1] = Math.max(r1, r2);
            }
        }
        return res;
    }

    public static int[][] copyArray(int[][] m) {
        int[][] res = new int[m.length][2];
        for (int i = 0; i < m.length; i++) {
            res[i][0] = m[i][0];
            res[i][1] = m[i][1];
        }
        return res;
    }

    public static void main(String[] args) {
        int testTime = 10000;
        int maxLen = 20;
        int maxValue = 100;
        for (int i = 0; i < testTime; i++) {
            int[][] m1 = generateArray(maxLen, maxValue);
            int[][] m2 = copyArray(m1);
            int ans1 = bestArrange1(m1);
            int ans2 = bestArrange2(m2);
            if (ans1 != ans2) {
                System.out.println("Fuck! fuck");
                System.out.println(Arrays.toString(m1));
                System.out.println(Arrays.toString(m2));
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("finish!!");
    }
}
