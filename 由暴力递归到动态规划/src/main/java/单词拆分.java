import java.util.ArrayList;
import java.util.List;

public class 单词拆分 {
    public static int allWays(String rest, List<String> wordDict) {
        if (rest == "") {
            return 1;
        }
        int res = 0;
        char[] target = rest.toCharArray();
        int N = target.length;
        for (String s : wordDict) {
            char[] str = s.toCharArray();
            if (!(target[0] == str[0] && rest.contains(s))) {
                continue;
            }
            int len = str.length;
            String nextRest = len > (N - 1) ? "" : rest.substring(len, N);
            res += allWays(nextRest, wordDict);
        }
        return res;
    }

    //动态规划
    public static boolean wordBreak(String s, List<String> wordDict) {
        if (allWays(s, wordDict) > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        String str = "leetcode";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("leet");
        wordDict.add("code");
        System.out.println(wordBreak(str, wordDict));
    }
}
