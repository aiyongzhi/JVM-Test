import java.util.HashMap;

public class 添加运算符拼凑target {
    //给你一个数组arr，在每一个数字之前，你都可以选择添加’+‘ or '-'
    //但是必须所有数字都参与
    //再给定一个数字target，请返回拼凑出target的方法数

    //给定一个数字arr，0...i-1已经决策好了，不需要你关心
    //i是现在来到的位置
    //target:要拼凑的数字
    //cur:现在已经算出的结果

    //很简单就知道暴力递归会存在重复解
    //动态规划
    public static int process(int[] arr, int i, int target, int cur) {
        if (i == arr.length) {
            return cur == target ? 1 : 0;
        }
        return process(arr, i + 1, target, cur + arr[i]) +
                process(arr, i + 1, target, cur - arr[i]);
    }

    public static int getWays(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return process(arr, 0, target, 0);
    }

    //加记忆化搜索优化
    //加一个哈希表
    public static int process2(int[] arr, int i, int target, int cur, HashMap<String, Integer>
            map) {
        String str = String.valueOf(i) + "_" + String.valueOf(cur);
        if (map.containsKey(str)) {
            return map.get(str);
        }
        if (i == arr.length) {
            return cur == target ? 1 : 0;
        }
        int ways = process2(arr, i + 1, target, cur + arr[i], map) +
                process2(arr, i + 1, target, cur - arr[i], map);
        map.put(str, ways);
        return map.get(str);
    }

    public static int getWays2(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        HashMap<String, Integer> map = new HashMap<>();
        return process2(arr, 0, target, 0, map);
    }

    //改成动态规划：动态规划是有严格位置依赖关系的结构
    //这题通过优化可以优化成背包问题
    //背包问题的暴力递归
    public static int process(int[] arr, int i, int rest) {
        if (i == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        int res = 0;
        res += process(arr, i + 1, rest);
        if (rest - arr[i] >= 0) {
            res += process(arr, i + 1, rest - arr[i]);
        }
        return res;
    }

    //直接改成动态规划好了
    public static int dpWays(int[] arr, int bag) {
        int N = arr.length;
        int[][] dp = new int[2][bag + 1];
        dp[N % 2][0] = 1;
        //填普遍位置
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= bag; rest++) {
                dp[index % 2][rest] = dp[(index + 1) % 2][rest];
                if (rest - arr[index] >= 0) {
                    dp[index % 2][rest] += dp[(index + 1) % 2][rest - arr[index]];
                }
            }
        }
        return dp[0][bag];
    }

    public static int getWays1(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0) {
                arr[i] = (-arr[i]);
            }
            sum += arr[i];
        }
        //如果最大值sum都比target小，一定凑不出来
        if (sum < target) {
            return 0;
        }
        //因为是同一堆数通过加减拼凑出来的，如果sum和target奇偶性不同，一定可能
        if (((sum & 1) ^ (target & 1)) != 0) {
            return 0;
        }
        int bag = (sum + target) >> 1;
        System.out.println(process(arr, 0, bag));
        return dpWays(arr, bag);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int target = 3;
        System.out.println(getWays(arr, target));
        System.out.println(getWays2(arr, target));
        System.out.println(getWays1(arr, target));
    }
}
