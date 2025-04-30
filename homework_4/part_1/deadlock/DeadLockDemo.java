package homework_4.part_1.deadlock;

public class DeadLockDemo {
    public static void main(String[] args) {
        TestResource source1 = new TestResource();
        TestResource source2 = new TestResource();
        new Thread(() -> {
            source1.addCountFrom(source2);
        }).start();

        new Thread(() -> {
            source2.addCountFrom(source1);

        }).start();
    }
}
