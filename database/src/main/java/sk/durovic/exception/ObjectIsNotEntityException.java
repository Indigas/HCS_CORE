package sk.durovic.exception;

public class ObjectIsNotEntityException extends RuntimeException{

    public ObjectIsNotEntityException(String message) {
        super(message);
    }
}
