package heapGreater;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

//加强堆
//用反向索引来实现加强堆
public class heapGreater<T> {
    private final ArrayList<T> heap;//堆的底层是用一个从0开始的连续数组实现的
    private final HashMap<T, Integer> indexMap;//反向索引表，可以知道某个元素在堆中的下标
    private int heapSize;
    private final Comparator<? super T> comp;

    public heapGreater(Comparator<T> comp) {
        this.comp = comp;
        heap = new ArrayList<>();
        indexMap = new HashMap<>();
        heapSize = 0;
    }

    //判断堆是否为空
    public boolean isEmpty() {
        return this.heapSize == 0;
    }

    //取出堆的大小
    public int size() {
        return heapSize;
    }

    //堆中是否包含某个元素obj
    public boolean contains(T obj) {
        return indexMap.containsKey(obj);
    }

    //堆顶的元素
    public T peek() {
        return heap.get(0);
    }

    //弹出堆顶的元素
    public T pop() {
        T ans = heap.get(0);
        Swap(0, heapSize - 1);
        indexMap.remove(ans);
        heap.remove(--heapSize);
        heapify(0);
        return ans;
    }

    //向堆中加入一个元素
    public void push(T obj) {
        heap.add(obj);
        indexMap.put(obj, heapSize);
        heapInsert(heapSize++);
    }

    //obj的值改变了，请把堆调对
    public void resign(T obj) {
        heapInsert(indexMap.get(obj));
        heapify(indexMap.get(obj));
    }

    //删除任意的元素
    public void remove(T obj) {
        T replace = heap.get(heapSize - 1);
        int index = indexMap.get(obj);
        indexMap.remove(obj);
        heap.remove(--heapSize);
        if (obj != replace) {
            heap.set(index, replace);
            indexMap.put(replace, index);
            resign(replace);
        }
    }

    //将某个元素由obj改成target
    public void set(T obj, T target) {
        int index = indexMap.get(obj);
        indexMap.remove(obj);
        indexMap.put(target, index);
        heap.set(index, target);
        resign(target);
    }

    //实现堆的基本操作
    //1:heapInsert
    private void heapInsert(int index) {
        while (comp.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
            Swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    //2:heapify
    private void heapify(int index) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int largest = (left + 1 < heapSize) && (comp.compare(heap.get(left + 1), heap.get(left)) < 0
            ) ? left + 1 : left;
            largest = comp.compare(heap.get(largest), heap.get(index)) < 0 ? largest : index;
            if (largest == index) {
                break;
            }
            Swap(index, largest);
            index = largest;
            left = index * 2 + 1;
        }
    }

    public List<T> getAllElement() {
        List<T> ans = new ArrayList<>();
        for (T t : heap) {
            ans.add((T) t);
        }
        return ans;
    }

    //实现一个交换方法
    private void Swap(int i, int j) {
        T o1 = heap.get(i);
        T o2 = heap.get(j);
        heap.set(i, o2);
        heap.set(j, o1);
        indexMap.put(o1, j);
        indexMap.put(o2, i);
    }
}
