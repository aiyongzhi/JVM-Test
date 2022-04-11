import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class 打印一个字符串的全排列 {
    //递归含义：str中的字符你还能选择，之前的选择生成的字符串在path内，
    //请返回选择str中的字符串全排列后把答案收集起来
    public static void process1(char[] chs, HashSet<Integer> set, String path, List<String> ans) {
        //base case
        if (set.size() == chs.length) {
            ans.add(path);
            return;
        }
        //还有字符可以选择
        for (int i = 0; i < chs.length; i++) {
            if (!set.contains(i)) {
                set.add(i);
                process1(chs, set, path + chs[i], ans);
                set.remove(i);
            }
        }
    }

    public static List<String> getStrAllPermutation1(String str) {
        if (str == null || str.length() == 0) {
            return new ArrayList<>();
        }
        char[] chs = str.toCharArray();
        List<String> ans = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();
        process1(chs, set, "", ans);
        return ans;
    }

    //上面那种方法递归参数设计的很差，不利于写动态规划
    //写动态规划需要递归可变参数尽可能是整型int
    //递归含义：当前index位置的字符可以和后面所有位置的元素交换，请收集所有的最终可能性
    //之前决策产生的效果全体现在了chs数组里面
    public static void process2(char[] chs, int index, List<String> ans) {
        //base case
        if (index == chs.length) {
            ans.add(String.valueOf(chs));
        } else {
            for (int i = index; i < chs.length; i++) {
                Swap(chs, index, i);
                process2(chs, index + 1, ans);
                Swap(chs, index, i);
            }
        }
    }

    public static void Swap(char[] chs, int i, int j) {
        char tmp = chs[i];
        chs[i] = chs[j];
        chs[j] = tmp;
    }

    public static List<String> getStrAllPermutation2(String str) {
        if (str == null || str.length() == 0) {
            return new ArrayList<>();
        }
        List<String> ans = new ArrayList<>();
        process2(str.toCharArray(), 0, ans);
        return ans;
    }

    //去除重复
    public static void process3(char[] chs, int index, List<String> ans) {
        if (index == chs.length) {
            ans.add(String.valueOf(chs));
        } else {
            boolean[] visited = new boolean[256];//记录index位置使用过的字符
            for (int i = index; i < chs.length; i++) {
                if (!visited[chs[i]]) {
                    visited[chs[i]] = true;
                    Swap(chs, i, index);
                    process3(chs, index + 1, ans);
                    Swap(chs, i, index);
                }
            }
        }
    }

    public static List<String> getStrAllPermutationNoRepeat(String str) {
        if (str == null || str.length() == 0) {
            return new ArrayList<>();
        }
        List<String> ans = new ArrayList<>();
        process3(str.toCharArray(), 0, ans);
        return ans;
    }

    public static void main(String[] args) {
        List<String> ans1 = getStrAllPermutation1("abc");
        System.out.println(Arrays.toString(ans1.toArray()));
        List<String> ans2 = getStrAllPermutation2("abc");
        System.out.println(Arrays.toString(ans2.toArray()));
    }
}
