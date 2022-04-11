import java.util.Random;

public class stepSum {
    //华为机试题：
    //stepSum问题
    //我们定义一种运算例如123的stepSum=123+12+1==146   12345的stepSum=12345+1234+123+12+1
    //由上述公式任意一个数num都可以求出其stepSum
    //现在给你一个正数num，判断其是否是另外一个数的stepSum
    //如果是则返回true，不是则返回false

    //由暴力解到最优解
    //要写一个方法，输入一个数，输出它的stepSum

    //暴力解
    public static boolean isStepSum1(int num) {
        //遍历1到num的每一个数，判断其stepSum是不是num
        for (int i = 1; i <= num; i++) {
            if (getStepSum(i) == num) {
                return true;
            }
        }
        return false;
    }

    //显然把1--num遍历一次的代价是O(N*logN)
    //优化方向肯定是减少遍历次数，即不需要把1--num遍历一次
    //很容易就想到二分查找
    public static int getStepSum(int num) {
        int tmp = num;
        while (tmp != 0) {
            num += (tmp / 10);
            tmp /= 10;
        }
        return num;
    }

    //经过测试：越大的数的stepSum越大
    public static boolean isStepSum2(int num) {
        int L = 1;
        int R = num;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (getStepSum(mid) < num) {
                L = mid + 1;
            } else if (getStepSum(mid) > num) {
                R = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        //写一个对数器测试最优解二分查找的正确性
        int testTime = 50000;
        int maxValue = 100000;
        Random random = new Random();
        boolean success = true;
        while (testTime-- != 0) {
            int num = random.nextInt(maxValue) + 1;
            if (isStepSum1(num) != isStepSum2(num)) {
                success = false;
                System.out.println(num);
            }
        }
        System.out.println(success ? "OK" : "Fake:fake!");
    }
}
