public class 归并拓展小和问题 {
    //排序思路：归并排序：让左边有序，右边有序，再合并让整体有序
    public static int mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    //递归思路：让arr[L....R]上变成有序
    public static int process(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int mid = L + ((R - L) >> 1);
        return process(arr, L, mid) +
                process(arr, mid + 1, R) +
                merge(arr, L, mid, R);
    }

    public static int merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int sum = 0;
        int i = 0;//定位help中的元素
        int p1 = L;//定位左半部分有序区域的元素
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            if (arr[p1] < arr[p2]) {
                sum += arr[p1] * (R - p2 + 1);
            }
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        //把剩余的添加上
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 2, 5};
        System.out.println(mergeSort(arr));
    }
}
