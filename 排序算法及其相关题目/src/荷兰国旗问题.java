import java.util.Arrays;

public class 荷兰国旗问题 {
    //简易问题版：
    //给定一个数组和一个划分值k
    //请把数组分成小于等于k的在左边，大于k的在右边
    public static void divide(int[] arr, int k) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int cur = 0;
        int lessOrEqual = -1;
        for (; cur < arr.length; cur++) {
            if (arr[cur] <= k) {
                Swap(arr, ++lessOrEqual, cur);
            }
        }
    }

    public static void Swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    //真正的荷兰国旗问题：分三块儿
    //将arr[L....R]范围分成 小于  等于  大于 三块
    public static int[] partition(int[] arr, int L, int R, int p) {
        int less = L - 1;
        int more = R + 1;
        while (L != more) {
            if (arr[L] < p) {
                Swap(arr, ++less, L++);
            } else if (arr[L] > p) {
                Swap(arr, --more, L);
            } else {
                L++;
            }
        }
        return new int[]{less + 1, more - 1};
    }
}
