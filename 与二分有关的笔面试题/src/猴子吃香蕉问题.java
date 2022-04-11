public class 猴子吃香蕉问题 {
    //现在有N堆香蕉，arr[i]表示第i堆香蕉的重量
    //现在门卫准备离开H小时，在这段时间，猴子准备偷吃香蕉
    //求出猴子在H小时内吃完所有香蕉需要的最小时速，即每小时吃的最小重量.
    //如果在H小时内无论如何都吃不完香蕉则返回-1
    //能吃完所有香蕉则返回最小时速
    //规定：1)对于猴子在每小时内吃完一堆香蕉后，还有能力吃下一堆香蕉的情况，我们不考虑。即猴子每小时只能
    //吃同一堆香蕉，不能吃完一堆后去吃另一堆

    //最优解二分
    //速度有一个范围：（1--max(arr[i])）在这个范围内查找：一定要往二分上面去想
    public static int getArrayMax(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int j : arr) {
            max = Math.max(j, max);
        }
        return max;
    }

    public static int minSpeed(int[] arr, int H) {
        //假设不存在
        int minSpeed = -1;
        int L = 1;
        int R = getArrayMax(arr);
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (wasteTime(arr, mid) > H) {
                L = mid + 1;
            } else {
                minSpeed = mid;
                R = mid - 1;
            }
        }
        return minSpeed;
    }

    //写一个函数，输入arr和速度speed，返回吃完所有香蕉的时间
    public static int wasteTime(int[] arr, int speed) {
        int sum = 0;
        for (int j : arr) {
            sum += ((j + speed - 1) / speed);
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println();
    }
}
