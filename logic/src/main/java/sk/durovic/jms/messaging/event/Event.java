package sk.durovic.jms.messaging.event;

import lombok.AllArgsConstructor;
import lombok.Data;

public abstract class Event<T> {

    protected T entity;

    public T getEntity() {
        return entity;
    }
}
