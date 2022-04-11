import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class 快速排序 {
    public static void quickSort(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        process(arr, L, R);
    }

    //递归含义：让arr[L...R] 范围内有序
    //每次在选取arr[L...R]内随机一个数作为划分值
    //把它与等于区域的最后一个元素交换
    public static void process(int[] arr, int L, int R) {
        //base case
        if (L >= R) {
            return;
        }
        Random random = new Random();
        Swap(arr, random.nextInt(R + 1 - L) + L, R);
        int[] p = partition(arr, L, R);
        process(arr, L, p[0] - 1);
        process(arr, p[0] + 1, R);
    }

    //真正的荷兰国旗问题：分三块儿
    //将arr[L....R]范围分成 小于  等于  大于 三块
    public static int[] partition(int[] arr, int L, int R) {
        int less = L - 1;
        int more = R;
        while (L != more) {
            if (arr[L] < arr[R]) {
                Swap(arr, ++less, L++);
            } else if (arr[L] > arr[R]) {
                Swap(arr, --more, L);
            } else {
                L++;
            }
        }
        Swap(arr, more, R);
        return new int[]{less + 1, more};
    }

    public static void Swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {6, 4, 1, 3, 9, 0, 2, 5, -3, 8};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
