package homework_4.part_1.liveLock.exmp1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class User {
    private final static int DELAY = 1000;
    private String name;
    private boolean isWaiting;

    ReentrantLock locker = new ReentrantLock();

    User(String name) {
        if (name == null) {
            throw new NullPointerException();
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void tryConnect(CommonSource source) {
        try {
            locker.tryLock(DELAY, TimeUnit.MILLISECONDS);
            source.setUser(this);
            source.setIsUsing(true);
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void useCommonSourse(CommonSource source) {
        isWaiting = true;
        String searchData = "The value will never changed";

        while (isWaiting) {
            if (!source.isActive()) {
                tryConnect(source);
                source.showUseInfo();
                continue;
            }

            if (!source.getIsSomeOneWait() && source.getUser() != this) {
                source.setisSomeOneWait(isWaiting);
                continue;
            }

            if (source.getIsSomeOneWait() && locker.isLocked()) {
                source.setIsUsing(false);
                locker.unlock();
                continue;
            }

            if (source.getUser() == this) {
                searchData = source.getData();
                isWaiting = false;
                source.setIsUsing(false);
                if (locker.isLocked()) {
                    locker.unlock();
                }
                System.out.println(searchData);
            }
        }

    }
}
