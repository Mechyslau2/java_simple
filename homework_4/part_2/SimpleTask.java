package homework_4.part_2;

import java.util.concurrent.atomic.AtomicInteger;

public class SimpleTask implements Runnable {
    private AtomicInteger value;

    public SimpleTask(int value) {
        this.value = new AtomicInteger(value);
    }

    private void showValue(int value) {
        System.out.println(value);
    }

    @Override
    public void run() {
        int result = value.get();
        showValue(result);
    }
}
