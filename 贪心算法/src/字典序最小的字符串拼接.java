import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class 字典序最小的字符串拼接 {
    //贪心策略的解法
    public static class MyComparator implements Comparator<String> {
        public int compare(String o1, String o2) {
            return (o1 + o2).compareTo(o2 + o1);
        }
    }

    public static String lowestStr1(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        Arrays.sort(strs, new MyComparator());
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str);
        }
        return sb.toString();
    }

    //暴力方法全排列求解
    //暴力递归
    public static String lowestStr2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        HashSet<Integer> use = new HashSet<>();
        ArrayList<String> ans = new ArrayList<>();
        process(strs, use, "", ans);
        String res = ans.size() == 0 ? "" : ans.get(0);
        for (int i = 1; i < ans.size(); i++) {
            if (ans.get(i).compareTo(res) < 0) {
                res = ans.get(i);
            }
        }
        return res;
    }

    public static void process(String[] strs, HashSet<Integer> use, String path,
                               ArrayList<String> ans) {
        if (use.size() == strs.length) {
            ans.add(path);
        } else {
            for (int i = 0; i < strs.length; i++) {
                if (!use.contains(i)) {
                    use.add(i);
                    process(strs, use, path + strs[i], ans);
                    use.remove(i);
                }
            }
        }
    }

    //写一个函数随机生成字符串数组
    public static String[] generateRandomArray(int maxLen, int maxValueLen) {
        int len = (int) (Math.random() * maxLen) + 1;
        String[] strs = new String[len];
        for (int i = 0; i < len; i++) {
            strs[i] = generateRandomString(maxValueLen);
        }
        return strs;
    }

    //写一个随机生成字符串的函数
    //假设这个字符串全是由小写字母组成
    public static String generateRandomString(int maxLen) {
        char[] chs = new char[26];
        for (int i = 0; i < 26; i++) {
            chs[i] = (char) ('a' + i);
        }
        int len = (int) (Math.random() * maxLen) + 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int index = (int) (Math.random() * 26);
            sb.append(chs[index]);
        }
        return sb.toString();
    }

    //拷贝数组的函数
    public static String[] copyArray(String[] strs) {
        String[] ans = new String[strs.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = strs[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        int maxValueLen = 5;
        int maxLen = 5;
        int testTime = 1000;
        for (int i = 0; i < testTime; i++) {
            String[] strs1 = generateRandomArray(maxLen, maxValueLen);
            String[] strs2 = copyArray(strs1);
            String ans1 = lowestStr1(strs1);
            String ans2 = lowestStr2(strs2);
            if (!ans1.equals(ans2)) {
                System.out.println(Arrays.toString(strs1));
                System.out.println(Arrays.toString(strs2));
                System.out.println("贪心算法的答案: " + ans1);
                System.out.println("暴力方法的答案: " + ans2);
                System.out.println("Fuck!fuck!!");
                break;
            }
        }
    }
}
