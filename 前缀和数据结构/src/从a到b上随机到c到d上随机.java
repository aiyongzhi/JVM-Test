import java.util.Random;

public class 从a到b上随机到c到d上随机 {
    //从A到B上随机：到C到D上随机
    public static class RandomBox {
        private int min;
        private int max;

        public RandomBox(int from, int to) {
            this.min = from;
            this.max = to;
        }

        public int random() {
            return this.min + (int) (Math.random() * (this.max - this.min + 1));
        }

        public int getMin() {
            return min;
        }

        public int getMax() {
            return max;
        }
    }

    //由等概率返回0到1，加工出等概率返回0和1
    public static int Rand01(RandomBox randomBox) {
        int from = randomBox.getMin();
        int to = randomBox.getMax();
        int rang = to - from;//生成[0..rang]范围内的数据
        int randNum = 0;
        int mid = rang / 2;
        boolean odd = (rang & 1) == 0;//判断from-to范围内数的个数是奇数还是偶数
        do {
            randNum = randomBox.random() - from;
        } while (odd && (randNum == mid));
        return randNum > mid ? 1 : 0;
    }

    //加工出等概率返回c到d
    public static int myRandom(int a, int b, int from, int to) {
        int rang = to - from;//[0..to-from]范围内随机生成数
        //1:计算0..rang需要多少位比特位
        int digit = 1;
        while (((1 << digit) - 1) < rang) {
            digit++;
        }
        //2:开始凑数据,如果数据越界则再随机一次
        int randomNum = 0;
        do {
            randomNum = 0;
            for (int i = 0; i < digit; i++) {
                randomNum += ((1 << i) * Rand01(new RandomBox(a, b)));
            }
        } while (randomNum > rang);
        return randomNum + from;
    }

    public static void main(String[] args) {
        int from = 2;
        int to = 7;
        int testTime = 500000;
        int[] count = new int[to - from + 1];
        while (testTime-- != 0) {
            count[myRandom(3, 5, from, to) - from]++;
        }
        for (int i = 0; i < count.length; i++) {
            System.out.println((i + from) + "出现了" + count[i] + "次！");
        }
    }

    //由不等概率返回0和1到等概率返回0和1
    public static int Random01() {
        int ans = 0;
        do {
            ans = f();
        } while (ans == f());
        return ans;
    }

    //f函数可以以不等概率返回0和1
    public static int f() {
        return Math.random() < 0.75 ? 1 : 0;
    }
}
