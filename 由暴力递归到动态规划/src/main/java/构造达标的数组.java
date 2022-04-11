import java.util.Arrays;

public class 构造达标的数组 {
    //题目：请构造出长度为N的合法数组，要求数组中对于任意位置的i<k<j，都满足arr[i]+arr[j]!=arr[k]*2
    public static int[] makeArray(int size) {
        if (size == 1) {
            return new int[]{1};
        }
        //长度不为1
        int halfSize = (size + 1) / 2;
        int[] help = makeArray(halfSize);
        int[] ans = new int[size];
        //生成达标的奇数序列
        int index = 0;
        for (; index < halfSize; index++) {
            ans[index] = help[index] * 2 + 1;
        }
        //生成达标的偶数序列
        for (int i = 0; index < size; i++, index++) {
            ans[index] = help[i] * 2;
        }
        return ans;
    }

    //写一个函数检验生成的数组的合法性
    public static boolean isValid(int[] arr) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            for (int k = i + 1; k < N; k++) {
                for (int j = k + 1; j < N; j++) {
                    if (arr[i] + arr[j] == 2 * arr[k]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int N = 7;
        int[] arr = makeArray(N);
        System.out.println(Arrays.toString(arr));
        System.out.println(isValid(arr));

    }
}
