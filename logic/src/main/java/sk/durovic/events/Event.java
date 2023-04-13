package sk.durovic.events;

import sk.durovic.result.Result;

import java.util.Collection;

public interface Event {
    EventAction getAction();
    void setAction(EventAction action);
    <T> Collection<T> getEntities();
    <T> void setEntities(Collection<T> entities);
}
