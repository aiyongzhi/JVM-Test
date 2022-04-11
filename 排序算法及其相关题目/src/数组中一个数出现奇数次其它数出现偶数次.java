public class 数组中一个数出现奇数次其它数出现偶数次 {
    //给你一个数组arr 其中只有一个数字出现奇数次，其它数字出现偶数次
    //请找出这个出现了奇数次的数字
    public static int getNumber(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        return eor;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 6, 4, 5, 1, 3, 4, 5};
        System.out.println(getNumber(arr));
    }
}
