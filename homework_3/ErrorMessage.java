package homework_3;

public enum ErrorMessage {
    READ_ERROR_MESSAGE("cannot read the file"), 
    WRITE_ERROR_MESSAGE("cannot write to the file"), 
    FILE_NOT_FOUND("File not found"), 
    FILE_NOT_CREATED("File is not created"), 
    FILENAME_IS_NULL("fileName is 'null' "), 
    MESSAGE_IS_NULL("Message is 'null' "), 
    STREAM_WRITE_READ("Error during read/write"), 
    INVALID_PATH("invalid path"), 
    IS_NOT_FILE("is not a file"), 
    INVALID_FILE_NAME("invalid fileName"),
    FILE_NOT_EXISTS("File doesn't exist");
    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getErrorMessage() {
        return message;
    }
}
