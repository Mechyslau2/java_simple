package homework_4.part_1.liveLock;

public class LiveLockDemo {
    public static void main(String[] args) {
        User user1 = new User("user 1");
        User user2 = new User("user 2");

        CommonSource sourse = new CommonSource();

        new Thread(() -> {
            user1.useCommonSourse(sourse);
        }).start();

        new Thread(() -> {
            user2.useCommonSourse(sourse);
        }).start();

    }
}
