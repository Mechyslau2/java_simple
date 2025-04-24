package homework_3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class SimpleReaderAndWriter {

    private static final String EMPTY_FILE = "File is empty";
    private final static String EXIT_KEY = "exit";
    private static final String MESSAGE_TO_EXIT = "Type " + "'" + EXIT_KEY + "'" + " to stop :";

    private File file;

    public SimpleReaderAndWriter() {}

    public SimpleReaderAndWriter(String fileName) throws FileException {
        if (fileName == null) {
            throw new FileException(ErrorMessage.FILENAME_IS_NULL.getErrorMessage());
        }
        if (new File(fileName).isFile()) {
            file = new File(fileName);
        } else {
            throw new FileException(fileName + " " + ErrorMessage.IS_NOT_FILE.getErrorMessage());
        }
    }

    public boolean createFile(String fileName) throws FileException {
        if (fileName == null) {
            throw new FileException(ErrorMessage.FILENAME_IS_NULL.getErrorMessage(),
                    new FileException());
        }
        try {
            file = new File(fileName);
            file.createNewFile();
        } catch (NullPointerException ex) {
           throw new FileException(ErrorMessage.INVALID_PATH.getErrorMessage()+" or "+ErrorMessage.INVALID_FILE_NAME.getErrorMessage(), ex); 
        } 
        catch (IOException ex) {
            throw new FileException(ErrorMessage.INVALID_PATH.getErrorMessage(), ex);
        }
        return file != null;
    }



    public void readAndPrint() throws FileException {
        if (file == null) {
            throw new FileException(ErrorMessage.FILE_NOT_CREATED.getErrorMessage());
        }
        try (FileReader fileReader = new FileReader(file.getName())) {
            if (file.length() == 0) {
                System.out.println(file.getName() + EMPTY_FILE);
            }
            int symbol;
            while ((symbol = fileReader.read()) != -1) {
                System.out.print((char) symbol);
            }
            if (symbol == -1) {
                System.out.println("");
            }
        } catch (FileNotFoundException | NullPointerException ex) {
            throw new FileException(ErrorMessage.FILE_NOT_FOUND.getErrorMessage(), ex);
        } catch (IOException ex) {
            throw new FileException(ErrorMessage.READ_ERROR_MESSAGE.getErrorMessage(), ex);
        }
    }

    public void write(String message) throws FileException {
        if (file == null && message == null) {
            throw new FileException(ErrorMessage.FILE_NOT_CREATED.getErrorMessage() + " or "
                    + ErrorMessage.MESSAGE_IS_NULL.getErrorMessage());
        }
        try (FileWriter fileReader = new FileWriter(file.getName())) {
            char[] chars = new char[message.length()];
            message.getChars(0, message.length(), chars, 0);
            fileReader.write(chars);

        } 
        catch (FileNotFoundException | NullPointerException ex) {
            throw new FileException(ErrorMessage.FILE_NOT_FOUND.getErrorMessage(), ex);
        } catch (IOException ex) {
            throw new FileException(ErrorMessage.WRITE_ERROR_MESSAGE.getErrorMessage(), ex);
        }
    }

    public void append(String message) throws FileException {
        if (file == null && message == null) {
            throw new FileException(ErrorMessage.FILE_NOT_CREATED.getErrorMessage() + " or "
                    + ErrorMessage.MESSAGE_IS_NULL.getErrorMessage());
        }
        try (FileWriter fileWriter = new FileWriter(file.getName(), true)) {
            fileWriter.append("\n" + message);
        } catch (FileNotFoundException | NullPointerException ex) {
            throw new FileException(ErrorMessage.FILE_NOT_FOUND.getErrorMessage(), ex);
        } catch (IOException ex) {
            throw new FileException(ErrorMessage.WRITE_ERROR_MESSAGE.getErrorMessage(), ex);
        }
    }


    public void appendWithConsole() throws FileException {
        if (file == null) {
            throw new FileException(ErrorMessage.FILE_NOT_CREATED.getErrorMessage());
        }
        try (BufferedReader buffer =
                new BufferedReader(new InputStreamReader(System.in, System.console().charset()));
                FileWriter fileWrite = new FileWriter(file.getName(), true)) {
            System.out.println(MESSAGE_TO_EXIT);
            String item;
            do {
                item = buffer.readLine();
                if (!item.equals(EXIT_KEY)) {
                    fileWrite.append("\n" + item);
                }

            } while (!item.equals(EXIT_KEY));
        } catch (FileNotFoundException ex) {
            throw new FileException(ErrorMessage.FILE_NOT_FOUND.getErrorMessage(), ex);
        } catch (IOException ex) {
            throw new FileException(ErrorMessage.STREAM_WRITE_READ.getErrorMessage(), ex);
        }
    }
}
