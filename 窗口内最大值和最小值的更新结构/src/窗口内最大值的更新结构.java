import java.util.*;

public class 窗口内最大值的更新结构 {
    //我们规定可以选择通过R++向窗口中加入一个元素
    //也可以选择通过L++将窗口中的一个元素移除
    //L指针和R指针均不回退

    //窗口内最大值的更新结构
    //利用双端队列来实现这个窗口，队列中存放的是数组元素的下标
    //R++向窗口中新增一个元素时，从最右侧开始将双端队列中小于等于当前元素的下标全部从尾部弹出，再加入
    //L++将窗口中删除一个元素时，从双端队列最左侧开始将过期的下标删除


    /*
     * 题目：假设一个固定大小为W的窗口，依次滑过arr.
     * 返回每一次滑出状况的最大值
     * 例如；arr=[4,3,5,4,3,3,6,7],W=3
     * 返回：[5,5,5,4,6,7]
     * */
    public static List<Integer> getMaxArray(int[] arr, int W) {
        if (arr == null || arr.length < W || W <= 0) {//数组长度小于窗口大小认为无效
            return new ArrayList<>();
        }
        //用双端队列来实现窗口最大值的更新结构
        LinkedList<Integer> maxQueue = new LinkedList<>();

        List<Integer> result = new ArrayList<>();
        int R = 0;
        for (; R < arr.length; R++) {
            //1:将arr[R]加入窗口
            while (!maxQueue.isEmpty() && (arr[maxQueue.peekLast()] <= arr[R])) {
                maxQueue.pollLast();
            }
            maxQueue.addLast(R);
            //2:如果窗口满了需要移除左边的元素
            if (!maxQueue.isEmpty() && R - W == maxQueue.peekFirst()) {
                maxQueue.pollFirst();
            }
            //3:收集答案
            if (R >= W - 1) {
                result.add(arr[maxQueue.peekFirst()]);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 5, 4, 3, 3, 6, 7};
        int W = 3;
        List<Integer> result = getMaxArray(arr, W);
        System.out.println(Arrays.toString(result.toArray()));
    }
}
