package homework_4.part_2;

import java.util.concurrent.atomic.AtomicInteger;

public class SimpleThread implements Runnable {
    private AtomicInteger value;
    private Provider provider;

    SimpleThread(int value, Provider provider) {
        if (provider == null) {
            throw new NullPointerException();
        }
        this.value = new AtomicInteger(value);
        this.provider = provider;
    }

    @Override
    public void run() {
        int result = value.get();
        provider.showValue(result);
    }
}
