package homework_3;

import java.io.IOException;

public class FileException extends IOException {
    private int errorCode;

    public FileException() {
        super();
    }

    public FileException(String message) {
        super(message);
    }

    public FileException(Throwable cause) {
        super(cause);
    }

    public FileException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileException(String message, Throwable cause, int code) {
        this(message, cause);
        this.errorCode = code;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
