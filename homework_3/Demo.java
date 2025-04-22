package homework_3;

public class Demo {
    public static void main(String[] args) throws FileException {
        SimpleReaderAndWriter simpleReaderAndWriter = new SimpleReaderAndWriter();
        try {
            simpleReaderAndWriter.createFile("test");
            simpleReaderAndWriter.write("test");
            simpleReaderAndWriter.readAndPrint();
            simpleReaderAndWriter.append("new message");
            simpleReaderAndWriter.readAndPrint();
            simpleReaderAndWriter.write("test 2");
            simpleReaderAndWriter.readAndPrint();
            simpleReaderAndWriter.appendWithConsole();
        } catch (FileException ex) {
            System.err.println(ex);
        }

    }
}
