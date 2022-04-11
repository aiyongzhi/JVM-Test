package 单源最短路径;

import java.util.ArrayList;
import java.util.HashMap;

//用加强堆加速查找权值最小的节点的过程
public class GreaterHeap {
    private final ArrayList<DisNode> heap;//堆的底层是一个数组
    private final HashMap<DisNode, Integer> indexMap;//反向索引表
    private int heapSize;

    public GreaterHeap() {
        heap = new ArrayList<>();
        indexMap = new HashMap<>();
        heapSize = 0;
    }

    //对堆中的对象的值进行改变
    public void set(DisNode obj, int distance) {
        int index = indexMap.get(obj);
        obj.distance = distance;
        resign(heap.get(index));
    }

    //要写一个小根堆的加强堆
    //往堆中加入元素
    public void push(DisNode obj) {
        heap.add(obj);
        indexMap.put(obj, heapSize);
        heapInsert(heapSize++);
    }

    //弹出堆中的元素
    public DisNode pop() {
        DisNode ans = heap.get(0);
        Swap(0, heapSize - 1);
        indexMap.remove(ans);
        heap.remove(--heapSize);
        heapify(0);
        return ans;
    }

    //先写一个Swap函数
    private void Swap(int i, int j) {
        DisNode o1 = heap.get(i);
        DisNode o2 = heap.get(j);
        heap.set(i, o2);
        heap.set(j, o1);
        indexMap.put(o1, j);
        indexMap.put(o2, i);
    }

    //写堆的两个基本操作 heapInsert 和 heapify
    //向上调堆
    private void heapInsert(int index) {
        if (heap.get(index).distance < heap.get((index - 1) / 2).distance) {
            Swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    //向下调堆
    private void heapify(int index) {
        int left = (index * 2) + 1;
        while (left < heapSize) {
            int largest = (left + 1) < heapSize && (heap.get(left + 1).distance >
                    heap.get(left).distance) ? left + 1 : left;
            largest = heap.get(index).distance > heap.get(largest).distance ? index : largest;
            if (largest == index) {
                break;
            }
            Swap(index, largest);
            index = largest;
            left = (index * 2) + 1;
        }
    }

    //当元素的值发生改变之后，请把堆调堆
    private void resign(DisNode obj) {
        heapInsert(indexMap.get(obj));
        heapify(indexMap.get(obj));
    }

    //判断堆是否为空
    public boolean isEmpty() {
        return heapSize == 0;
    }
}
