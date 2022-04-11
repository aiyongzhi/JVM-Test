import java.util.Arrays;
import java.util.Random;

public class 基数排序 {
    //基于桶思想的排序
    //只能用于十进制数据的比较
    //十分考验coding能力
    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        radixSort(arr, 0, arr.length - 1, maxBits(arr));
    }

    public static int maxBits(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int res = 0;
        while (max != 0) {
            res++;
            max /= 10;
        }
        return res;
    }

    public static void radixSort(int[] arr, int L, int R, int digit) {
        //默认的桶数
        final int radix = 10;
        int i = 0;
        int j = 0;
        int[] bucket = new int[R - L + 1];
        //每一位依次进行一次入桶出桶操作
        for (int d = 1; d <= digit; d++) {
            int[] count = new int[radix];
            for (i = L; i <= R; i++) {
                j = getDigit(arr[i], d);
                count[j]++;
            }
            for (i = 1; i < radix; i++) {
                count[i] = count[i - 1] + count[i];
            }
            //进行倒出桶中数据的操作
            for (i = R; i >= L; i--) {
                j = getDigit(arr[i], d);
                bucket[count[j] - 1] = arr[i];
                count[j]--;
            }
            //把桶中的数据拷贝回来，成功完成一次操作
            for (i = L, j = 0; i <= R; i++, j++) {
                arr[i] = bucket[j];
            }
        }
    }

    //拿到一个数的每一位
    public static int getDigit(int n, int d) {
        return ((n / (int) Math.pow(10, d - 1)) % 10);
    }

    //写一个比较器：
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean success = true;
        while (testTime-- != 0) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            radixSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                success = false;
                System.out.println(Arrays.toString(arr1));
                System.out.println(Arrays.toString(arr2));
                break;
            }
        }
        System.out.println(success ? "Nice!" : "Fucking fucked!");
    }

    public static boolean isEqual(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }
        int N = arr1.length;
        for (int i = 0; i < N; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    public static int[] copyArray(int[] arr) {
        int N = arr.length;
        int[] ret = new int[N];
        for (int i = 0; i < N; i++) {
            ret[i] = arr[i];
        }
        return ret;
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        Random random = new Random();
        int[] arr = new int[random.nextInt(maxSize + 1)];
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            arr[i] = random.nextInt(maxValue + 1);
        }
        return arr;
    }
}
