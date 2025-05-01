package homework_4.part_1.liveLock.exmp1;

public class MyUserThread implements Runnable {
    private User user;
    private CommonSource source;

    MyUserThread(User user, CommonSource source) {
        if (user == null || source == null) {
            throw new NullPointerException();
        }
        this.user = user;
        this.source = source;
    }

    @Override
    public void run() {
        user.useCommonSourse(source);
    }
}
