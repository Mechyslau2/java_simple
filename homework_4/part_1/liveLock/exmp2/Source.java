package homework_4.part_1.liveLock.exmp2;

import java.util.concurrent.atomic.AtomicInteger;

public class Source implements Runnable {
    private boolean triger = true;
    private AtomicInteger value;

    public Source(int value) {
        this.value = new AtomicInteger(value);
    }

    public synchronized void showValue() {
        triger = true;
        int tempValue = value.get();

        while (triger) {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " is waiting");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(tempValue);
            triger = false;
            notify();
        }
    }

    @Override
    public void run() {
        showValue();
    }
}
