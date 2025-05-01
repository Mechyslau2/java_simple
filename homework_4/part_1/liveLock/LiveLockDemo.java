package homework_4.part_1.liveLock;

public class LiveLockDemo {
    public static void main(String[] args) {
        User user1 = new User("user 1");
        User user2 = new User("user 2");
        CommonSource sourse = new CommonSource();

        MyUserThread thread1 = new MyUserThread(user1, sourse);
        MyUserThread thread2 = new MyUserThread(user2, sourse);

        new Thread(thread1).start();
        new Thread(thread2).start();
    }
}
