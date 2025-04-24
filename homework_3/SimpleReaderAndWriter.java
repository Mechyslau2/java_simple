package homework_3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class SimpleReaderAndWriter {

    private static final String EMPTY_FILE = "File is empty";
    private static final String EXIT_KEY = "exit";
    private static final String MESSAGE_TO_EXIT = "Type " + "'" + EXIT_KEY + "'" + " to stop :";

    private static final String FILE_NOT_CREATED = ErrorMessage.FILE_NOT_CREATED.getErrorMessage();
    private static final String INVALID_PATH = ErrorMessage.INVALID_PATH.getErrorMessage();
    private static final String INVALID_FILENAME = ErrorMessage.INVALID_FILE_NAME.getErrorMessage();
    private static final String FILE_NOT_FOUND = ErrorMessage.FILE_NOT_FOUND.getErrorMessage();
    private static final String FILE_IS_NULL = ErrorMessage.FILENAME_IS_NULL.getErrorMessage();
    private static final String IS_NOT_FILE = ErrorMessage.IS_NOT_FILE.getErrorMessage();
    private static final String READ_ERROR_MESSAGE =
            ErrorMessage.READ_ERROR_MESSAGE.getErrorMessage();
    private static final String WRITE_ERROR_MESSAGE =
            ErrorMessage.WRITE_ERROR_MESSAGE.getErrorMessage();
    private static final String STREAM_WRITE_READ =
            ErrorMessage.STREAM_WRITE_READ.getErrorMessage();
    private static final String MESSAGE_IS_NULL = ErrorMessage.MESSAGE_IS_NULL.getErrorMessage();
    private static final String FILE_NOT_EXISTS = ErrorMessage.FILE_NOT_EXISTS.getErrorMessage();

    private File file;

    private void checkElIsNull(Object el) {
        if (el == null) {
            throw new FileException(FILE_IS_NULL);
        }
    }


    private void checkElIsNull(Object el, String message) {
        if (message == null) {
            checkElIsNull(el);
        }
        if (el == null && message != null) {
            throw new FileException(message);
        }
    }

    private void checkIsFileExists(File file) {
        if (!file.exists()) {
            throw new FileException(FILE_NOT_EXISTS);
        }
    }

    public SimpleReaderAndWriter() {}

    public SimpleReaderAndWriter(String fileName) throws FileException {
        checkElIsNull(fileName);
        if (new File(fileName).isFile()) {
            file = new File(fileName);
        } else {
            throw new FileException(fileName + " " + IS_NOT_FILE);
        }
    }

    public boolean createFile(String fileName) throws FileException {
        checkElIsNull(fileName);
        try {
            file = new File(fileName);
            file.createNewFile();
        } catch (NullPointerException ex) {
            throw new FileException(INVALID_PATH + " or " + INVALID_FILENAME, ex);
        } catch (IOException ex) {
            throw new FileException(INVALID_PATH, ex);
        }
        return file != null;
    }



    public void readAndPrint() throws FileException {
        checkElIsNull(file, FILE_NOT_CREATED);
        checkIsFileExists(file);
        try (BufferedReader bufferedReader =
                new BufferedReader(new FileReader(file.getName(), StandardCharsets.UTF_8))) {
            if (file.length() == 0) {
                System.out.println(file.getName() + EMPTY_FILE);
            }
            int symbol;
            while ((symbol = bufferedReader.read()) != -1) {
                System.out.print((char) symbol);
            }
            System.out.println("");
        } catch (FileNotFoundException | NullPointerException ex) {
            throw new FileException(FILE_NOT_FOUND, ex);
        } catch (IOException ex) {
            throw new FileException(READ_ERROR_MESSAGE, ex);
        }
    }

    public void rewriteFileContent(String message) throws FileException {
        checkElIsNull(file, FILE_NOT_CREATED + " or " + INVALID_PATH);
        checkIsFileExists(file);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file.getName()))) {
            char[] chars = new char[message.length()];
            message.getChars(0, message.length(), chars, 0);
            bufferedWriter.write(chars);

        } catch (FileNotFoundException | NullPointerException ex) {
            throw new FileException(FILE_NOT_FOUND, ex);
        } catch (IOException ex) {
            throw new FileException(WRITE_ERROR_MESSAGE, ex);
        }
    }

    public void append(String message) throws FileException {
        checkElIsNull(file, FILE_NOT_CREATED + " or " + MESSAGE_IS_NULL);
        checkIsFileExists(file);
        try (BufferedWriter bufferedWriter =
                new BufferedWriter(new FileWriter(file.getName(), true))) {
            bufferedWriter.append("\n" + message);
        } catch (FileNotFoundException | NullPointerException ex) {
            throw new FileException(FILE_NOT_FOUND, ex);
        } catch (IOException ex) {
            throw new FileException(WRITE_ERROR_MESSAGE, ex);
        }
    }


    public void appendWithConsole() throws FileException {
        checkElIsNull(file, FILE_NOT_CREATED);
        checkIsFileExists(file);
        try (BufferedReader bufferReader =
                new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
                BufferedWriter bufferWriter =
                        new BufferedWriter(new FileWriter(file.getName(), true))) {
            System.out.println(MESSAGE_TO_EXIT);
            String item;
            do {
                item = bufferReader.readLine();
                if (!item.equals(EXIT_KEY)) {
                    bufferWriter.append("\n" + item);
                }

            } while (!item.equals(EXIT_KEY));
        } catch (FileNotFoundException ex) {
            throw new FileException(FILE_NOT_FOUND, ex);
        } catch (IOException ex) {
            throw new FileException(STREAM_WRITE_READ, ex);
        }
    }
}
