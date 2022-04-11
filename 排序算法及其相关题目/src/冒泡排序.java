import java.util.Arrays;

public class 冒泡排序 {
    //相邻的元素两两比较，大的元素往后排
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        for (int rest = N - 1; rest > 0; rest--) {
            for (int i = 0; i < rest; i++) {
                if (arr[i] > arr[i + 1]) {
                    Swap(arr, i, i + 1);
                }
            }
        }
    }

    public static void Swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static void main(String[] args) {
        int[] arr = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
