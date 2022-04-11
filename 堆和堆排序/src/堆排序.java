public class 堆排序 {
    public void Swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public void heapInsert(int[] arr, int index) {
        //停下来的条件
        //1:不比父亲大了
        //2:index来到0位置后不会再往上移动
        while (arr[index] > arr[(index - 1) / 2]) {
            Swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public void headify(int[] arr, int index, int heapSize) {
        int left = 2 * index + 1;//左孩子的下标
        //在有孩子的情况下才会下沉
        //终止条件:没有孩子的时候
        while (left < heapSize) {
            int largest = (left + 1 < heapSize) && (arr[left + 1] > arr[left]) ? left + 1 : left;
            largest = arr[index] > arr[largest] ? index : largest;
            if (largest == index) {
                break;
            }
            Swap(arr, index, largest);
            index = largest;
            left = index * 2 + 1;
        }
    }

    //从上向下建立堆适合用户一个一个数给你
    //从下往上建立堆的时间复杂度O(N),确实要优秀，但这只能应用于用户一股脑的把数据全给你,例如：数组排序
    //堆排序的时间复杂度：要用常数增倍法来估计
    public void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        //一:把N个数调整为大根堆
        //法一：时间复杂度为O(N*logN)
/*        for(int i=0;i<arr.length;i++){
            heapInsert(arr,i);
        }*/
        //法二：假设完全二叉树已经建立好了，从下往上建立大根堆O(N)
        for (int i = arr.length - 1; i >= 0; i--) {
            headify(arr, i, arr.length);
        }
        //二：开时，把最大值放最后，堆大小减一
        int heapSize = arr.length;
        Swap(arr, 0, --heapSize);
        headify(arr, 0, heapSize);
        while (heapSize > 0) {
            Swap(arr, 0, --heapSize);
            headify(arr, 0, heapSize);
        }
    }
}
