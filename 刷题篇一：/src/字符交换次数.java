public class 字符交换次数 {
    //现在有一个数组arr，其中只由’G‘和字符'B'组成
    //现在请你将所有G移动到左边所有的B移动到右边
    //但你只能相邻元素两两交换
    //请问想要达成目标，至少需要交换几次

    public static int minSwapTimes(char[] str) {
        if (str == null || str.length == 0) {
            return 0;
        }
        //贪心策略：每次交换都保证G向左移动，B向右移动，且相同字符不交换
        //L指针表示：字符G要交换到的位置
        //R指针：向右找G交换到L位置
        //指针移动规则
        //1:R指针指向的字符不是G：R++
        //2:R指针指向的字符是G: R++ L++
        //因为要求的是交换次数，因此没必要真正的交换
        int sum = 0;
        int L = 0;
        int R = 0;
        for (; R < str.length; R++) {
            if (str[R] == 'G') {
                sum += (R - L);
                L++;
            }
        }
        return sum;
    }
}
