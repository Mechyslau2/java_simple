package homework_4.part_1.liveLock.exmp2;


public class MyThread implements Runnable {
    Source source1;
    Source source2;

    MyThread(Source source1, Source source2) {
        if (source1 == null || source2 == null) {
            throw new NullPointerException();
        }
        this.source1 = source1;
        this.source2 = source2;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        boolean isGetAllSource = false;
        
        while (!isGetAllSource) {
            String dataFromSource1 = source1.getSource();
            String dataFromSource2 = source2.getSource();

            boolean isAvailabelSource1 = Thread.holdsLock(source1);
            boolean isAvailabelSource2 = Thread.holdsLock(source2);

            if (isAvailabelSource1 && isAvailabelSource2) {
                System.out.println(name+" "+dataFromSource1+" "+dataFromSource2);
                isGetAllSource = true;
            }

        }
    }
}
