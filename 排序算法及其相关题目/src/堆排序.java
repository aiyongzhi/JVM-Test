import java.util.Arrays;

public class 堆排序 {
    //堆的底层结构一般用数组
    //其逻辑结构是完全二叉树
    //向堆中添加一个元素使之构成大根堆

    //现在[0.....index-1]上已经是大根堆
    //加入index下标的元素使整体还是大根堆
    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            Swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    //将任意下标为index 位置的元素下沉到大根堆中对应位置
    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        //有孩子并且,自身比较大孩子小，才需要下沉
        while (left < heapSize) {
            //把较大孩子的下标传给largest
            int largest = left + 1 < heapSize
                    && arr[left + 1] > arr[left]
                    ? left + 1 : left;
            largest = arr[index] > arr[largest] ? index : largest;
            if (largest == index) {
                break;
            }
            //有孩子，且左右孩子的最大值要大于index，这时index一定会下沉
            Swap(arr, index, largest);
            index = largest;
            left = index * 2 + 1;
        }
    }

    //将arr下标i和j的元素互换
    //在已经建立好的大根堆中拿走最大值
    public static int popMax(int[] arr, int heapSize) {
        int res = arr[0];
        Swap(arr, 0, heapSize - 1);
        heapSize--;
        heapify(arr, 0, heapSize);
        return res;
    }

    public static void Swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    //堆排序
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        //for(int i=0;i<arr.length;i++){//时间复杂度为O(N*logN)
        //  heapInsert(arr,i);
        //}
        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }
        int heapSize = arr.length;
        while (heapSize > 0) {
            Swap(arr, 0, --heapSize);
            heapify(arr, 0, heapSize);
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 4, 3, 9, 2, 7, -1, 4, -2};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
