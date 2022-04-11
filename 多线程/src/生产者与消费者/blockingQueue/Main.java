package 生产者与消费者.blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    public static void main(String[] args) {
        BlockingQueue<Good> blockingQueue = new ArrayBlockingQueue<Good>(5);
        Producer producer = new Producer(blockingQueue);
        Consumer consumer = new Consumer(blockingQueue);
        new Thread(producer).start();
        new Thread(consumer).start();

    }
}
