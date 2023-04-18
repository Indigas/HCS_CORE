package sk.durovic.exceptions;

public class IllegalRequest extends RuntimeException{

    public IllegalRequest() {
    }

    public IllegalRequest(String message) {
        super(message);
    }

    public IllegalRequest(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalRequest(Throwable cause) {
        super(cause);
    }

    public IllegalRequest(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
