import java.util.HashMap;

public class 贴纸拼单词 {
    //力扣链接：https://leetcode-cn.com/problems/stickers-to-spell-word/
    //暴力递归：枚举所有贴纸作为当前的第一张贴纸去拼凑出target，后序过程走递归，返回最小张数
    public static int minStickers1(String[] stickers, String target) {
        if (stickers == null || stickers.length == 0 || target == null || target.length() == 0) {
            return 0;
        }
        int ans = process1(stickers, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static int process1(String[] stickers, String target) {
        //base case
        if (target.length() == 0) {
            return 0;
        }
        int minSticker = Integer.MAX_VALUE;
        for (String first : stickers) {
            String next = minus(target, first);
            //其实是一个二叉树的剪枝
            if (next.length() != target.length()) {
                minSticker = Math.min(process1(stickers, next), minSticker);
            }
        }
        return minSticker + (minSticker == Integer.MAX_VALUE ? 0 : 1);
    }

    public static String minus(String target, String sticker) {
        char[] str1 = target.toCharArray();
        char[] str2 = sticker.toCharArray();
        int[] count = new int[26];
        for (int i = 0; i < str1.length; i++) {
            count[str1[i] - 'a']++;
        }
        for (int i = 0; i < str2.length; i++) {
            count[str2[i] - 'a']--;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                for (int k = 0; k < count[i]; k++) {
                    sb.append((char) ('a' + i));
                }
            }
        }
        return sb.toString();
    }

    //对上述暴力递归优化
    //1)不用每次在minus方法中都遍历生成贴纸的词频
    public static int minStickers2(String[] stickers, String target) {
        if (stickers == null || stickers.length == 0 || target == null || target.length() == 0) {
            return 0;
        }
        //预先生成贴纸的词频
        int N = stickers.length;
        int[][] count = new int[N][26];
        for (int i = 0; i < N; i++) {
            char[] chs = stickers[i].toCharArray();
            for (int j = 0; j < chs.length; j++) {
                count[i][chs[j] - 'a']++;
            }
        }
        int ans = process2(count, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static int process2(int[][] stickers, String target) {
        //base case
        if (target.length() == 0) {
            return 0;
        }
        int[] count = new int[26];
        char[] chs = target.toCharArray();
        //1：生成目标的词频
        for (int i = 0; i < chs.length; i++) {
            count[chs[i] - 'a']++;
        }
        int N = stickers.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            if (stickers[i][chs[0] - 'a'] > 0) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (count[j] > 0) {
                        int times = count[j] - stickers[i][j];
                        for (int k = 0; k < Math.max(times, 0); k++) {
                            sb.append((char) (j + 'a'));
                        }
                    }
                }
                String next = sb.toString();
                min = Math.min(process2(stickers, next), min);
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    //记忆化搜索 加缓存
    public static int minStickers(String[] stickers, String target) {
        if (stickers == null || stickers.length == 0 || target == null || target.length() == 0) {
            return 0;
        }
        HashMap<String, Integer> dp = new HashMap<>();
        dp.put("", 0);
        int N = stickers.length;
        int[][] count = new int[N][26];
        for (int i = 0; i < N; i++) {
            char[] chs = stickers[i].toCharArray();
            for (int j = 0; j < chs.length; j++) {
                count[i][chs[j] - 'a']++;
            }
        }
        int ans = process3(count, target, dp);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static int process3(int[][] stickers, String target, HashMap<String, Integer>
            dp) {
        if (dp.containsKey(target)) {
            return dp.get(target);
        }
        int[] count = new int[26];
        char[] chs = target.toCharArray();
        //1：生成目标的词频
        for (int i = 0; i < chs.length; i++) {
            count[chs[i] - 'a']++;
        }
        int N = stickers.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            if (stickers[i][chs[0] - 'a'] > 0) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (count[j] > 0) {
                        int times = count[j] - stickers[i][j];
                        for (int k = 0; k < Math.max(times, 0); k++) {
                            sb.append((char) (j + 'a'));
                        }
                    }
                }
                String next = sb.toString();
                min = Math.min(process2(stickers, next), min);
            }
        }
        dp.put(target, min + (min == Integer.MAX_VALUE ? 0 : 1));
        return dp.get(target);
    }

    public static void main(String[] args) {
        String[] stickers = {"with", "example", "science"};
        String target = "thehat";
        System.out.println(minStickers2(stickers, target));

    }
}
