public class 二分查找 {
    //在排序数组中利用二分查找某数
    //假设是升序数组
    //返回这个数，在数组中的下标
    public static int binarySearch(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int L = 0;
        int R = arr.length;
        while (L <= R) {
            int mid = (L + R) >> 1;
            if (arr[mid] > target) {
                R = mid - 1;
            } else if (arr[mid] < target) {
                L = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int target = 7;
        int ret = binarySearch(arr, target);
        System.out.println(ret);
    }
}
