package bfprt;

import java.util.Arrays;


//方法一:改进快排的方式
//主要利用快排partition操作的过程
//时间复杂度可以收敛到O(N)
public class 无序数组第k小 {
    public static int getKMin1(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0 || k > arr.length) {
            return Integer.MAX_VALUE;//返回整型最大值表示，数据输入不合法
        }
        return process1(arr, 0, arr.length - 1, k - 1);
    }

    /*
     * 递归含义:请你在arr[L...R]范围内找到数组中第index小的数
     * 请返回这个数值
     * 这里的快排并没有真正的排序
     * */
    public static int process1(int[] arr, int L, int R, int index) {
        //来到下游说明还没有找到
        if (L == R) {
            return arr[L];//隐含着:L==R==index
        }
        //随机选取一个划分值
        //L+[0,R-L]随机
        int pivot = arr[L + (int) (Math.random() * (R - L + 1))];
        int[] range = partition(arr, L, R, pivot);
        if (index >= range[0] && index <= range[1]) {
            return arr[index];
        } else if (index < range[0]) {
            return process1(arr, L, range[0] - 1, index);
        } else {
            return process1(arr, range[1] + 1, R, index);
        }
    }

    //快排的partition过程
    public static int[] partition(int[] arr, int L, int R, int pivot) {
        int less = L - 1;
        int more = R + 1;
        int cur = L;
        while (cur < more) {
            if (arr[cur] < pivot) {
                swap(arr, ++less, cur++);
            } else if (arr[cur] > pivot) {
                swap(arr, --more, cur);
            } else {
                cur++;
            }
        }
        return new int[]{less + 1, more - 1};//只有等于区域的左边界<=右边界时才有等于区
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    //将递归改成迭代的方式
    public static int getKMin2(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0 || k > arr.length) {
            return Integer.MAX_VALUE;//返回整型最大值表示，数据输入不合法
        }
        int L = 0;
        int R = arr.length - 1;
        int index = k - 1;
        int[] rang;
        int pivot = 0;
        while (L < R) {
            pivot = arr[L + (int) (Math.random() * (R - L + 1))];
            rang = partition(arr, L, R, pivot);
            if (index < rang[0]) {
                R = rang[0] - 1;
            } else if (index > rang[1]) {
                L = rang[1] + 1;
            } else {
                return pivot;
            }
        }
        return arr[L];
    }

    //写个对数器来验证
    public static int compareWays(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0 || k > arr.length) {
            return Integer.MAX_VALUE;//返回整型最大值表示，数据输入不合法
        }
        Arrays.sort(arr);
        return arr[k - 1];
    }

    //随机生成数组
    public static int[] getGenerateRandomArray(int maxValue, int maxLen) {
        int len = (int) (Math.random() * maxLen) + 1;
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }

    //拷贝数组
    public static int[] copyArray(int[] arr) {
        int[] res = new int[arr.length];
        System.arraycopy(arr, 0, res, 0, res.length);
        return res;
    }

    public static void main(String[] args) {
        int testTime = 10;
        int maxValue = 100;
        int maxLen = 100;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = getGenerateRandomArray(maxValue, maxLen);
            int[] arr2 = copyArray(arr1);
            int k = 0;
            do {
                k = (int) (Math.random() * maxLen) + 1;
            } while (k <= arr1.length);
            int ans1 = compareWays(arr1, k);
            int ans2 = getKMin2(arr2, k);
            if (ans1 != ans2) {
                System.out.println(Arrays.toString(arr1));
                System.out.println(k);
                System.out.println("Fuck!");
                break;
            }
        }
        System.out.println("Ops!");
    }
}
