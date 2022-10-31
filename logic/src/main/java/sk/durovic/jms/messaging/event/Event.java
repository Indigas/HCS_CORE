package sk.durovic.jms.messaging.event;

public abstract class Event<T> {

    protected T entity;

    public T getEntity() {
        return entity;
    }
}
