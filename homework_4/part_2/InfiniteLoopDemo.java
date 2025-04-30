package homework_4.part_2;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class InfiniteLoopDemo {
    public static void main(String[] args) {
        Provider provider = new Provider();
        SimpleThread thread1 = new SimpleThread(1, provider);
        SimpleThread thread2 = new SimpleThread(2, provider);
        ScheduledExecutorService threadsServise  = Executors.newScheduledThreadPool(2);

        threadsServise.scheduleWithFixedDelay(thread1, 0, 2, TimeUnit.SECONDS);
        threadsServise.scheduleWithFixedDelay(thread2, 1, 2, TimeUnit.SECONDS);
    }
}
