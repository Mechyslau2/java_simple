package homework_4.part_1.liveLock.exmp2;

public class LivelockDemo2 {
    public static void main(String[] args) {
        Source source = new Source("Source 1");
        Source source2 = new Source("Source 2");
        MyThread thread1 = new MyThread(source, source2);
        MyThread thread2 = new MyThread(source2, source);

        new Thread(thread1).start();
        new Thread(thread2).start();
    }
}
