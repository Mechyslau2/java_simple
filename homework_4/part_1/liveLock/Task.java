package homework_4.part_1.liveLock;

public class Task implements Runnable {
    Source source1;
    Source source2;


    public Task(Source source1, Source source2) {
        if (source1 == null || source2 == null) {
            throw new NullPointerException();
        }
        this.source1 = source1;
        this.source2 = source2;
    }

    @Override
    public void run() {
        boolean isGetAllSource = false;
        String data1 = null, data2 = null;

        while (!isGetAllSource) {
            if (source1.lock.tryLock()) {
                data1 = source1.getSourceData();
                if (source2.lock.tryLock()) {
                    data2 = source2.getSourceData();
                }
            }

            if (data1 != null && data2 != null) {
                isGetAllSource = true;
                System.out.println(data1 + " " + data2);
            }

        }
    }
}
