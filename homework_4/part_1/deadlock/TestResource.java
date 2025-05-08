package homework_4.part_1.deadlock;

import java.util.concurrent.atomic.AtomicInteger;

public class TestResource {
    private final static int DELAY = 500;
    private AtomicInteger counter = new AtomicInteger(0);

    public synchronized void addCountFrom(TestResource source) {
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(DELAY);
            int result = source.getCounter();
            counter.getAndAdd(result);
        } catch (InterruptedException ex) {
            ex.getStackTrace();
        }
    }

    public synchronized int getCounter() {
        System.out.println(Thread.currentThread().getName());
        return counter.get();
    }
}
