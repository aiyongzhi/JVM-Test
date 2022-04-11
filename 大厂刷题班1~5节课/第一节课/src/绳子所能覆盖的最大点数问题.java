public class 绳子所能覆盖的最大点数问题 {
    /*
     * 题目描述:
     * 给定一个有序数组arr，代表坐落在x轴上的点
     * 给定一个正数k代表绳子的长度
     * 返回绳子最多压中几个点?
     * 即使绳子的边缘盖住点也计算在内
     * */
    /*
     * 左神讲的窗口模型
     * */
    public static int maxCoverPoint(int[] arr, int K) {
        //双指针 时间复杂度为O(N)
        if (arr == null || arr.length == 0 || K <= 0) {
            return 0;
        }
        int R = 0;
        int max = 0;
        for (int L = 0; L < arr.length; L++) {
            while (R < arr.length && arr[R] - arr[L] <= K) {
                R++;
            }
            max = Math.max(max, R - L);
        }
        return max;
    }

    public static void main(String[] args) {

    }
}
