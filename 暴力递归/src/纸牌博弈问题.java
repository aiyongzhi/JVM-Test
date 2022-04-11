public class 纸牌博弈问题 {
    //现在有N张纸牌排成一条线，纸牌的牌面值都是正数
    //现在有A选手和B选手开始拿纸牌，但规定，每次拿纸牌的时候只能拿最左侧的牌和最右侧的牌
    //给定一个数组arr,代表这N张纸牌，arr[i]表示第i张纸牌的牌面值
    //我们假设A选手和B选手都绝顶聪明，请返回获胜者的分数

    //作为先手，在arr[L...R]范围上拿牌获得的最大分数
    public static int f(int[] arr, int L, int R) {
        //base case
        if (L == R) {
            return arr[L];
        }
        //L<R
        int p1 = arr[L] + s(arr, L + 1, R);
        int p2 = arr[R] + s(arr, L, R - 1);
        return Math.max(p1, p2);
    }

    public static int s(int[] arr, int L, int R) {
        //base case
        if (L == R) {
            return 0;
        }
        int p1 = f(arr, L + 1, R);
        int p2 = f(arr, L, R - 1);
        return Math.min(p1, p2);
    }

    public static int winnerScore(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int A = f(arr, 0, arr.length - 1);
        int B = s(arr, 0, arr.length - 1);
        return Math.max(A, B);
    }

    //加缓存，写成记忆化搜索
    public static int f1(int[] arr, int L, int R, int[][] fMap, int[][] sMap) {
        if (fMap[L][R] != -1) {
            return fMap[L][R];
        }
        //没算过
        //base case
        int ans = 0;
        if (L == R) {
            ans = arr[L];
        } else {
            int p1 = arr[L] + s1(arr, L + 1, R, sMap, fMap);
            int p2 = arr[R] + s1(arr, L, R - 1, sMap, fMap);
            ans = Math.max(p1, p2);
        }
        fMap[L][R] = ans;
        return ans;
    }

    public static int s1(int[] arr, int L, int R, int[][] sMap, int[][] fMap) {
        if (sMap[L][R] != -1) {
            return sMap[L][R];
        }
        //没算过
        //base case
        int ans = 0;
        if (L < R) {
            int p1 = f1(arr, L + 1, R, fMap, sMap);
            int p2 = f1(arr, L, R - 1, fMap, sMap);
            ans = Math.min(p1, p2);
        }
        sMap[L][R] = ans;
        return ans;
    }

    public static int winnerScore1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] fMap = new int[N][N];
        int[][] sMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                fMap[i][j] = -1;
                sMap[i][j] = -1;
            }
        }
        int A = f1(arr, 0, N - 1, fMap, sMap);
        int B = s1(arr, 0, N - 1, sMap, fMap);
        return Math.max(A, B);
    }

    //精细化的动态规划
    public static int dpWays(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dpF = new int[N][N];
        int[][] dpS = new int[N][N];
        //填初始化的位置
        for (int i = 0; i < N; i++) {
            dpF[i][i] = arr[i];
        }
        for (int i = 1; i < N; i++) {
            int L = 0;
            int R = i;
            while (L < N && R < N) {
                dpF[L][R] = Math.max(arr[L] + dpS[L + 1][R], arr[R] + dpS[L][R - 1]);
                dpS[L][R] = Math.min(dpF[L + 1][R], dpF[L][R - 1]);
                L++;
                R++;
            }
        }
        return Math.max(dpF[0][N - 1], dpS[0][N - 1]);
    }

    public static void main(String[] args) {
        int[] arr = {4, 6, 3, 8, 2, 9, 2, 3, 10, 12};
        System.out.println(winnerScore(arr));
        System.out.println(winnerScore1(arr));
        System.out.println(dpWays(arr));
    }
}
