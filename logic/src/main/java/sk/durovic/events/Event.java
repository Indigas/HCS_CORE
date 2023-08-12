package sk.durovic.events;

import java.util.Collection;

public interface Event {
    EventAction getAction();
    void setAction(EventAction action);
    Collection<?> getEntities();
    void setEntities(Collection<?> entities);
}
