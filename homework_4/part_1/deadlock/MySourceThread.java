package homework_4.part_1.deadlock;

public class MySourceThread implements Runnable {
    private TestResource source1;
    private TestResource source2;

    MySourceThread(TestResource source1, TestResource source2) {
        if (source1 == null || source2 == null) {
            throw new NullPointerException();
        }
        this.source1 = source1;
        this.source2 = source2;
    }

    @Override
    public void run() {
        source1.addCountFrom(source2);
    }
}
