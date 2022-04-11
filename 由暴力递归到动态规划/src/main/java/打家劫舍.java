public class 打家劫舍 {
    //力扣198：打家劫舍
    //链接：https://leetcode-cn.com/problems/house-robber/
    public int rob(int[] nums) {
        int N = nums.length;
        int[] dp = new int[N + 1];
        dp[N] = 0;
        dp[N - 1] = nums[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            dp[i] = Math.max(dp[i + 1], dp[i + 2] + nums[i]);
        }
        return dp[0];
    }
}
