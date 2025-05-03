package homework_4.part_1.deadlock;

public class FixedDeadLockDemo {
    public static void main(String[] args) {
        TestResource source1 = new TestResource();
        TestResource source2 = new TestResource();
        MyTask taks1 = new MyTask(source1, source2);
        MyTask task2 = new MyTask(source2, source1);

        new Thread(taks1).start();
        new Thread(task2).start();
    }
}
