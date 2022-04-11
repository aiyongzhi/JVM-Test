public class 堆下沉操作 {
    //含义：现在某个数在index位置上，让这个数向下沉到它应该在的位置上
    public void Swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
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
}
