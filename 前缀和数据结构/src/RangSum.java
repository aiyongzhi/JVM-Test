public class RangSum {
    //数据结构解决的问题：对于一个数组arr
    //我要频繁（数亿次）的调用sum方法求arr[L....R]上的和
    //请设计一个数据结构上，让每次查询的代价尽可能低
    public class rangSum {
        private int[] preSum;

        public rangSum(int[] arr) {
            int N = arr.length;
            preSum = new int[N];
            for (int i = 0; i < N; i++) {
                preSum[i] = (i - 1 >= 0 ? preSum[i - 1] : 0) + arr[i];
            }
        }

        public int rangSum(int L, int R) {
            return L == 0 ? preSum[R] : (preSum[R] - preSum[L - 1]);
        }
    }
}
