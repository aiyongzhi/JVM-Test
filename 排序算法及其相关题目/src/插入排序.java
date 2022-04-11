import java.util.Arrays;
import java.util.Random;

public class 插入排序 {
    //由0-i范围内变为有序一直递推到0-N-1范围内有序
    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        //arr.length>=2需要排序
        for (int i = 1; i < arr.length; i++) {
            //想让0--i范围内有序，我们认为0..i-1已经有序了，不需要我们考虑了
            //现在只要让i位置的数归为即可
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                Swap(arr, j, j + 1);
            }
        }
    }

    public static void Swap(int[] arr, int i, int j) {
        //不会出现相同数据交换 可以用异或
        //solution1 :
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
        //arr[i]=arr[i]^arr[j]
        //arr[j]=arr[i]^arr[j]
        //arr[i]=arr[i]^arr[j]
    }

    public static void main(String[] args) {
        //写一个对数器测试
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean success = true;
        while (testTime-- != 0) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            insertionSort(arr1);
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
            arr[i] = random.nextInt(maxValue + 1) - random.nextInt(maxValue + 1);
        }
        return arr;
    }
}
