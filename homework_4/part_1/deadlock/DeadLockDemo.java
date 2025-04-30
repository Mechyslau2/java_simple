package homework_4.part_1.deadlock;

public class DeadLockDemo {
    public static void main(String[] args) {
        TestResource source1 = new TestResource();
        TestResource source2 = new TestResource();
        MySourceThread thread1 = new MySourceThread(source1, source2);
        MySourceThread thread2 = new MySourceThread(source2, source1);

        new Thread(thread1).start();
        new Thread(thread2).start();
    }
}
