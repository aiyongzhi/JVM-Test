public class 找大于等于目标值的最左边的数 {
    //在一个升序的有序数组中
    //请找出大于等于目标值的最小值
    //返回这个数字
    public static int getTheMinMoreOrEqualsK(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int L = 0;
        int R = arr.length - 1;
        int ret = Integer.MAX_VALUE;
        while (L <= R) {
            int mid = (L + R) >> 1;
            if (arr[mid] >= target) {
                ret = Math.min(ret, arr[mid]);
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 6, 8, 9, 13, 21};
        int target = 10;
        System.out.println(getTheMinMoreOrEqualsK(arr, target));
    }
}
