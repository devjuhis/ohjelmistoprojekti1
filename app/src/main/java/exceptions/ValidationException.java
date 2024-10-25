package exceptions;

public class ValidationException extends RuntimeException {
    private final String message;
    private final int errorCode;

    public ValidationException(String message, int errorCode) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public int getErrorCode() {
        return errorCode;
    }
}

