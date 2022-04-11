import java.util.HashSet;
import java.util.LinkedList;

//总结：滑动窗口往往用来解决窗口内的最值问题
//因为窗口的L和R指针都是不会回退的,因此解决问题的时候判断是否能用滑动窗口来优化
//主要取决于问题本身是否与求数组arr[L....R]内最大小值有关
//还取决于问题本身的性质，即让L和R均不回退，这个特性一般需要去问题中通过分析找出来
public class 滑动窗口中的最大值 {
    //利用单调双端队列实现
    //加数逻辑：加入一个数时，把队列中比它小的全从尾部弹出
    //减数逻辑：减去一个数的时候，查看头部数据是否过期，如果过期则从头部弹出
    //双端队列中放的是数组中元素的下标
    //因为判断过期的时候是靠下标判断的，同样也可以用arr[index]来判断
    //经典题目：假设有一个长度为w的窗口，给定你一个数组arr，请返回窗口滑过arr时，
    //每个位置窗口的最大值
    //arr=[4,3,5,4,3,3,6,7] w=3
    //[5,5,5,4,6,7]


    public static int[] getMaxWindows(int[] arr, int w) {
        if (arr == null || arr.length == 0 || w < 1 || arr.length < w) {
            return null;
        }
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        LinkedList<Integer> queueMax = new LinkedList<>();
        for (int R = 0; R < arr.length; R++) {
            //1:将arr[R]加入窗口
            while (!queueMax.isEmpty() && (arr[queueMax.peekLast()] <= arr[R])) {
                queueMax.pollLast();
            }
            queueMax.addLast(R);
            //如果满了三个元素就可以删除
            if (R - w == queueMax.peekFirst()) {
                queueMax.pollFirst();
            }
            if (R >= w - 1) {
                res[index++] = arr[queueMax.pollFirst()];
            }
        }

        return res;
    }
}
