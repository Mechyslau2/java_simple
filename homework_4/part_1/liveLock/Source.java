package homework_4.part_1.liveLock;

import java.util.concurrent.locks.ReentrantLock;

public class Source {
    private String source;
    public ReentrantLock lock = new ReentrantLock();

    public Source(String source) {
        if (source == null || lock == null) {
            throw new NullPointerException();
        }
        this.source = source;
    }

    public String getSourceData() {
        try {
            lock.lock();
            return source;
        } finally {
            lock.unlock();
        }
      
    }

}
