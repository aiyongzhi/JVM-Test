import java.util.Arrays;

public class 堆积木问题初阶版本 {
    //京东笔试题：
    //给你一个数值arr，arr[i]表示第i块积木的质量
    //现在有如下规则，给你N快积木，摆积木时必须遵循以下两个原则，
    //1):你想把一块积木摆在另一块积木上面，必须保证上面的积木质量不小于下面积木的质量
    //2):同时你还得保证，上面积木与下面积木的质量差不超过x
    //现在给定一个长度为N的数值arr和x，请你给出至少要摆多少堆
    public static int allHeap(int[] arr, int x) {
        if (arr == null || arr.length == 0 || x < 0) {
            return 0;
        }
        Arrays.sort(arr);
        int res = 0;
        int i = 0;//每一堆的开始
        int j = 0;
        while (i < arr.length) {
            res++;
            while (j + 1 < arr.length && (arr[j + 1] - arr[j] < x)) {
                j++;
            }
            i = j + 1;
            j = i;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {7, 4, 1, 9, 13};
        int x = 3;
        System.out.println(allHeap(arr, x));
    }
}
