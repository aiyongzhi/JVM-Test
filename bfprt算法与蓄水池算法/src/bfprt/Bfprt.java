package bfprt;

//步骤
//1:将原数组每五个分成一组
//2:组内进行排序
//3:拿出每组的中位数，构成一个中位数数组
//4:以这个中位数数组中的中位数作为划分值
//5:进行荷兰国旗问题划分区域
//6:用二分加速查找
public class Bfprt {
    public static int bfprt(int[] arr, int L, int R, int index) {
        if (L == R) {
            return arr[L];//L==R==index
        }
        int pivot = medianOfMedians(arr, L, R);
        int[] range = partition(arr, L, R, pivot);
        //命中直接返回，如果没有命中就二分
        if (index >= range[0] && index <= range[1]) {
            return arr[index];
        } else if (index < range[0]) {
            return bfprt(arr, L, range[0] - 1, index);
        } else {
            return bfprt(arr, range[1] + 1, R, index);
        }
    }

    private static int[] partition(int[] arr, int L, int R, int pivot) {
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

    private static int medianOfMedians(int[] arr, int L, int R) {
        int size = R - L + 1;
        int offset = size % 5 == 0 ? 0 : 1;
        int[] mArr = new int[size / 5 + offset];
        for (int team = 0; team < mArr.length; team++) {
            //每一组的开头位置
            int teamStart = L + team * 5;
            //每组找中点 L...L+4 L+5....L+9...
            mArr[team] = findMedian(arr, teamStart, Math.min(teamStart + 5, R));
        }
        return bfprt(mArr, 0, mArr.length - 1, mArr.length / 2);
    }

    private static int findMedian(int[] arr, int L, int R) {
        insertionSort(arr, L, R);
        return arr[(L + R) / 2];
    }

    private static void insertionSort(int[] arr, int L, int R) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = L + 1; i <= R; i++) {
            for (int j = i - 1; j >= L && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {

    }
}
