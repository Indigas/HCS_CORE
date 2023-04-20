package sk.durovic.events;

import java.util.Collection;
import java.util.Map;

public interface Event {
    EventAction getAction();
    void setAction(EventAction action);
    Collection<?> getEntities();
    void setEntities(Collection<?> entities);
}
