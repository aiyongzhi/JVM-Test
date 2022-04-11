public class 位运算实现加减乘除 {
    //用位运算实现加法
    //加法等于进位相加和无进位相加的和
    public static int Add1(int a, int b) {
        //用b来存储进位
        //如果b等于0表示没有进位，直接返回
        if (b == 0) {
            return a;
        }
        //还有进位，则把无进位相加的结果加上进位相加
        return Add1(a ^ b, (a & b) << 1);
    }

    //迭代写法
    public static int Add2(int a, int b) {
        int sum = a;
        while (b != 0) {
            sum = a ^ b;
            b = (a & b) << 1;
            a = sum;
        }
        return sum;
    }

    //用位运算实现减法
    //a-b<=>a+(-b)
    public static int getOppNum(int num) {
        return Add1(~num, 1);
    }

    public static int sub(int a, int b) {
        return Add1(a, getOppNum(b));
    }

    //用位运算实现乘法:
    public static int multiply(int a, int b) {
        int res = 0;
        while (b != 0) {
            if ((b & 1) != 0) {
                res = Add1(res, a);
            }
            b >>= 1;
            a <<= 1;
        }
        return res;
    }

    //用位运算实现除法:只支持整数除法
    //对于除法时，我们习惯上把数据全转换成正数后再进行除法
    public static boolean isNeg(int num) {//判断一个数是不是负数
        return num < 0;
    }

    public static int negNum(int num) {//将一个数变为它的相反数
        return Add1(~num, 1);
    }

    public static int div(int a, int b) {
        int x = isNeg(a) ? negNum(a) : a;
        int y = isNeg(b) ? negNum(b) : b;
        int res = 0;
        for (int i = 30; i >= 0; i = sub(i, 1)) {
            if ((x >> i) >= y) {
                res |= (1 << i);
                x = sub(x, (y << i));
            }
        }
        return (isNeg(a) ^ isNeg(b)) ? negNum(res) : res;
    }
    //Integer.MIN_VALUE:是转换不出最小值的，所以要单独讨论

    public static int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == Integer.MIN_VALUE) {
            return 1;
        }
        //不都是整型最小值
        else if (divisor == Integer.MIN_VALUE) {
            return 0;
        } else if (dividend == Integer.MIN_VALUE) {
            //我们规定 整型最小/-1等于整型最大值
            if (divisor == negNum(1)) {
                return Integer.MAX_VALUE;
            } else {
                int ans = div(Add1(dividend, 1), divisor);
                ans = Add1(ans, div(sub(dividend, multiply(ans, divisor)), divisor));
                return ans;
            }
        } else {
            return div(dividend, divisor);
        }
    }

    public static void main(String[] args) {
        System.out.println(Add1(4, 5));
        System.out.println(sub(3, 6));
        System.out.println(multiply(-4, 5));
        System.out.println(div(6, 4));
        System.out.println(div(-98, 5));
        System.out.println(divide(7, -3));
        System.out.println(0 % 2);
    }
}
