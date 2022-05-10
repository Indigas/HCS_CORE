package sk.durovic.exception;

public class EntityChangeVersion extends RuntimeException{
    public EntityChangeVersion(String message) {
        super(message);
    }
}
