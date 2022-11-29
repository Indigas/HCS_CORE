package sk.durovic.exception;

public class OperationNotSupported extends RuntimeException{
    public OperationNotSupported(String message) {
        super(message);
    }

    public OperationNotSupported() {
    }
}
