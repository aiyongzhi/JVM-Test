import java.util.HashMap;
import java.util.TreeSet;

public class 小于等于k的最大子数组的累加和 {
    //算法：遍历到每个元素时，加入到sum中去，并查找是否存在>=sum-k的最小子矩阵
    public static int getMaxLessOrEqualK(int[] nums, int K) {
        //arr中数字任意：可正，可以是负数，可以为0
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];//每次出现新行的时候加一次
            if (set.ceiling(sum - K) != null) {//有大于等于sum-k的最小元素
                max = Math.max(max, sum - set.ceiling(sum - K));
            }
            set.add(sum);
        }
        return max;
    }
}
