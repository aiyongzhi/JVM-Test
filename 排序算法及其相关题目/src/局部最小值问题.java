public class 局部最小值问题 {
    //题目：如果arr[0]<arr[1] 则arr[0]就是局部最小
    //如果arr[N-1]<arr[N-2] 则arr[N-1]就是局部最小
    //否则在arr[1...N-2]上必有局部最小
    //给你一个无序数组arr 数组中任意相邻元素不相同，请返回一个局部最小
    public static int getLocalMin(int[] arr) {
        if (arr == null || arr.length == 0) {
            return Integer.MIN_VALUE;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        //找局部最小
        int N = arr.length;
        if (arr[0] < arr[1]) {
            return arr[0];
        }
        if (arr[N - 1] < arr[N - 2]) {
            return arr[N - 1];
        }
        int L = 0;
        int R = N - 1;
        while (L <= R) {
            int mid = (L + R) >> 1;
            if (arr[mid] > arr[mid - 1]) {
                R = mid - 1;
            } else if (arr[mid] > arr[mid + 1]) {
                L = mid + 1;
            } else {
                return arr[mid];
            }
        }
        return Integer.MIN_VALUE;
    }

    public static void main(String[] args) {
        int[] arr = {3, 6, 7, 5};
        System.out.println(getLocalMin(arr));
    }
}
