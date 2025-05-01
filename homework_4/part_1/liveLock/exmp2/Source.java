package homework_4.part_1.liveLock.exmp2;

public class Source {
    private String source;


    Source(String source) {
        if (source == null) {
            throw new NullPointerException();
        }
        this.source = source;
    }

    private void doSomeAction() {
        int num = 0;
        for (int i = 0; i < 2000000; i++) {
            num = +i;
            if (num % 1000 == 0) {
                System.out.println(num);
            }
        }
        System.out.println(num);
    }

    public synchronized String getSource() {
        try {
            wait(10);
            doSomeAction();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        notify();
        return source;
    }

}
