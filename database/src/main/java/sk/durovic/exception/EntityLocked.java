package sk.durovic.exception;

public class EntityLocked extends RuntimeException{
    public EntityLocked(String message) {
        super(message);
    }
}
