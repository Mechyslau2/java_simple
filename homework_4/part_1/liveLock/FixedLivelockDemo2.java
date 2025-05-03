package homework_4.part_1.liveLock;

public class FixedLivelockDemo2 {
    public static void main(String[] args) {
        Source source = new Source("Source 1");
        Source source2 = new Source("Source 2");
        Task task1 = new Task(source, source2);
        Task task2 = new Task(source2, source);

        new Thread(task1).start();
        new Thread(task2).start();
    }
}
