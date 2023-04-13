package sk.durovic.events;

import java.util.Collection;

public interface Event {
    EventAction getAction();
    void setAction(EventAction action);
    <T> Collection<T> getEntities();
    <T> void setEntities(Collection<T> entities);
}
