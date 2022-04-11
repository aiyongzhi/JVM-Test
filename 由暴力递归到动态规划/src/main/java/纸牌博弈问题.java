public class 纸牌博弈问题 {
    public static int getMaxSorce(int[] arr) {
        int N = arr.length;
        return Math.max(f(arr, 0, N - 1), s(arr, 0, N - 1));
    }

    public static int f(int[] arr, int L, int R) {
        //base case
        if (L == R) {
            return arr[L];
        }
        //有不止一张牌
        return Math.max(arr[L] + s(arr, L + 1, R), arr[R] + s(arr, L, R - 1));
    }

    //注意当前牌堆中后手是无法拿牌的
    public static int s(int[] arr, int L, int R) {
        //base
        if (L == R) {
            return 0;
        }
        return Math.min(f(arr, L + 1, R), f(arr, L, R - 1));
    }
    //二维动态规划嵌套

    public static int dpWays(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dpF = new int[N][N];
        int[][] dpS = new int[N][N];
        for (int i = 0; i < N; i++) {
            dpF[i][i] = arr[i];
        }
        for (int i = 1; i < N; i++) {
            int R = i;
            int L = 0;
            while (R < N && L < N) {
                dpF[L][R] = Math.max(arr[L] + dpS[L + 1][R], arr[R] + dpS[L][R - 1]);
                dpS[L][R] = Math.min(dpF[L + 1][R], dpF[L][R - 1]);
                L++;
                R++;
            }
        }
        return Math.max(dpF[0][N - 1], dpS[0][N - 1]);
    }

    public static void main(String[] args) {
        int[] arr = {1, 4, 2, 8, 3, 5};
        System.out.println(getMaxSorce(arr));
        System.out.println(dpWays(arr));
    }
}
