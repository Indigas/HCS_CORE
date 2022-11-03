package sk.durovic.jms.messaging.exception;

public class OperationNotSupported extends RuntimeException{

    public OperationNotSupported() {
    }

    public OperationNotSupported(String message) {
        super(message);
    }
}
