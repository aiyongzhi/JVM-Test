package bfprt;

import java.util.Arrays;

import static java.util.Arrays.*;

//题意:给定你一个无序数组arr,和一个正整数k(1<=k<=arr.length)
//请返回一个数组，数组中存放的是arr前k大的数，要求返回的数组是一个按照降序排序的数组
/*
 * 解法一:暴力直接排序时间复杂度为 O(N*logN)
 * 解法二:自下向上建立大根堆时间复杂度为O(N+K*logN)
 * 解法三:找到第k+1大的数，再遍历数组收集所有比它大的数,
 * 时间复杂度为O(N+K*logK)
 *
 * */
public class TopK {
    //暴力解法
    public static int[] getTopK1(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0 || k > arr.length) {
            return null;
        }
        sort(arr);
        int[] res = new int[k];
        int index = 0;
        for (int i = arr.length - 1; i >= (arr.length - k); i--) {
            res[index++] = arr[i];
        }
        return res;
    }

    //堆的解法
    //要手写堆的heapify
    private static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            //孩子中最大值下标
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            //三者中最大值的下标
            largest = arr[index] > arr[largest] ? index : largest;
            if (largest == index) {
                break;
            }
            swap(arr, index, largest);
            index = largest;
            left = index * 2 + 1;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static int[] getTopK2(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0 || k > arr.length) {
            return null;
        }
        int[] res = new int[k];
        int index = 0;
        //自下向上调成大根堆
        int N = arr.length;
        for (int i = N - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }
        int heapSize = arr.length;
        res[0] = arr[0];
        swap(arr, 0, --heapSize);
        heapify(arr, 0, heapSize);
        for (index = 1; index < res.length; index++) {
            res[index] = arr[0];
            swap(arr, 0, --heapSize);
            heapify(arr, 0, heapSize);
        }
        return res;
    }

    //解法三:bfqrt或者改写快排
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

    public static int getKMin(int[] arr, int k) {
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

    public static int[] getTok3(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0 || k > arr.length) {
            return null;
        }
        int[] res = new int[k];
        //求出第k+1大的数
        int pivot = getKMin(arr, arr.length - k);
        int i = arr.length - k;//数组arr的下标
        int index = 0;//数组res的下标
        for (; i < arr.length; i++) {
            res[index++] = arr[i];
        }
        //前k大的数再res中只需要排序就OK
        Arrays.sort(res);
        reverse(res);
        return res;
    }

    private static void reverse(int[] arr) {
        int L = 0;
        int R = arr.length - 1;
        while (L < R) {
            swap(arr, L++, R--);
        }
    }

    //测试 对数器
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

    //判断两个数组是否相同
    private static boolean isEquals(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int testTime = 10;
        int maxValue = 100;
        int maxLen = 100;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = getGenerateRandomArray(maxValue, maxLen);
            int[] arr2 = copyArray(arr1);
            int[] arr3 = copyArray(arr1);
            int k;
            do {
                k = (int) (Math.random() * maxLen) + 1;
            } while (k >= arr1.length);
            int[] ans1 = getTopK1(arr1, k);
            int[] ans2 = getTopK2(arr2, k);
            int[] ans3 = getTok3(arr3, k);
            if ((!isEquals(ans1, ans2)) || (!isEquals(ans1, ans3))) {
                System.out.println(Arrays.toString(arr1));
                System.out.println("k==" + k);
                System.out.println(Arrays.toString(ans1));
                System.out.println(Arrays.toString(ans2));
                System.out.println(Arrays.toString(ans3));
                System.out.println("Fuck!");
                break;
            }
        }
        System.out.println("Ops!");
    }
}
