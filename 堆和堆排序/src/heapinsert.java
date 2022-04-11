public class heapinsert {
    //堆结构从逻辑层面是一颗完全二叉树
    //从物理角度来说是用数组储存的，从下标arr[0...i]表示一个堆
    //堆分为大根堆和小根堆两种。
    //大根堆：完全二叉树中所有子树的头节点是这颗子树的最大值
    //小根堆：                               的最小值
    //在index位置加入了一个数，请调整保证整体是一颗完全二叉树
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
}
