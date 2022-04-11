package 策略模式.可比较类排序的实现;

import java.util.Arrays;
import java.util.Comparator;

public class Sort {
    public static void sort(Object[] arr, Comparator comparator) {
        Arrays.sort(arr, comparator);
    }
}
