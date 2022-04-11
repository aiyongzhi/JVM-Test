import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class 打印字符串的全部子序列 {
    //str[0..i]的决策已经处理完了，现在处理str[i+1...]以后的序列
    //str[i]的内容都可以选择要或者不要
    public static void process1(char[] chs, int i, String path, List<String> ans) {
        //base case
        if (i == chs.length) {
            ans.add(path);
            return;
        }
        //还有字符可以决策
        process1(chs, i + 1, path, ans);
        process1(chs, i + 1, path + chs[i], ans);
    }

    public static List<String> getStrAllSubsequence(String str) {
        if (str == null || str.length() == 0) {
            return new ArrayList<>();
        }
        char[] chs = str.toCharArray();
        List<String> ans = new ArrayList<>();
        process1(chs, 0, "", ans);
        return ans;
    }

    //如果要去重用HashSet结构存答案即可,不会加入重复的字面值
    public static void process2(char[] chs, int i, String path, HashSet<String> ans) {
        //base case
        if (i == chs.length) {
            ans.add(path);
            return;
        }
        //还有字符可以决策
        //不要当前字符
        process2(chs, i + 1, path, ans);
        //要当前字符
        process2(chs, i + 1, path + chs[i], ans);
    }

    public static List<String> getStrAllSubsequenceNoRepeat(String str) {
        if (str == null || str.length() == 0) {
            return new ArrayList<>();
        }
        HashSet<String> ans = new HashSet<>();
        char[] chs = str.toCharArray();
        process2(chs, 0, "", ans);
        List<String> result = new ArrayList<>(ans);
        return result;
    }

    public static void main(String[] args) {
        List<String> ans = getStrAllSubsequence("abc");
        System.out.println(Arrays.toString(ans.toArray()));
        List<String> ans2 = getStrAllSubsequenceNoRepeat("aab");
        System.out.println(Arrays.toString(ans2.toArray()));
    }
}
