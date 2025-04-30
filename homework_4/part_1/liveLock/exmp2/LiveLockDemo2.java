package homework_4.part_1.liveLock.exmp2;

public class LiveLockDemo2 {
   public static void main(String[] args) {
      Source source = new Source(1);

      new Thread(source).start();
      new Thread(source).start();
   }
}
