import java.util.HashMap;

public class 拼字符串所需要的最少贴纸数 {
    //因为 target种类太多不方便改成精细化动态规划  此处用记忆化搜索
    public static int minStickers(String[] stickers, String target) {
        //进行一次洗数据
        if (stickers == null || stickers.length == 0 || target == null || target.length() == 0) {
            return 0;
        }
        //生成一个记录所有贴纸词频统计的map
        int N = stickers.length;
        int[][] map = new int[N][26];
        for (int i = 0; i < N; i++) {
            char[] chs = stickers[i].toCharArray();
            for (char c : chs) {
                map[i][c - 'a']++;
            }
        }
        //表示拼凑成String 需要的最小的贴纸数value
        HashMap<String, Integer> dp = new HashMap<>();
        dp.put("", 0);
        return process1(target, map, dp);
    }

    //记忆化搜索  动态规划
    public static int process1(String rest, int[][] map, HashMap<String, Integer> dp) {
        //记忆化搜索
        if (dp.containsKey(rest)) {
            return dp.get(rest);
        }
        //生成rest的词频统计
        int N = map.length;
        char[] target = rest.toCharArray();
        int[] restMap = new int[26];
        for (char c : target) {
            restMap[c - 'a']++;
        }
        //
        int ans = Integer.MAX_VALUE;//先认为这个贴纸不可能拼凑成
        for (int i = 0; i < N; i++) {
            //防止死递归 贴纸中包含rest的首字母的才走这条分支进行递归
            if (map[i][target[0] - 'a'] == 0) {
                continue;
            }
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 26; j++) {
                if (restMap[j] > 0) {
                    for (int k = 0; k < Math.max(0, map[i][j] - restMap[j]); k++) {
                        sb.append(j + 'a');
                    }
                }
            }
            String nextRest = sb.toString();
            int tmp = process1(nextRest, map, dp);
            if (tmp != -1) {
                ans = Math.min(ans, tmp + 1);
            }
        }
        dp.put(rest, ans == Integer.MAX_VALUE ? -1 : ans);
        return dp.get(rest);
    }

    public static void main(String[] args) {
        String[] stickers = {"abcc", "bcca", "bbba"};
        String target = "abcccbaacbb";
        System.out.println(minStickers(stickers, target));
    }
}
