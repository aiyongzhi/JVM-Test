package heapGreater;

import java.util.Comparator;

public class Test {
    public static class minComparator implements Comparator<Integer> {
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }

    public static void main(String[] args) {
        heapGreater<Integer> heapGreater = new heapGreater<>(new minComparator());
        for (int i = 0; i < 5; i++) {
            heapGreater.push(i);
        }
        for (int i = 0; i < 5; i++) {
            heapGreater.remove(i);
        }
    }
}
