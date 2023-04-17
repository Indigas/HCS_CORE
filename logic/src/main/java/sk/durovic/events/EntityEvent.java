package sk.durovic.events;

import java.util.Collection;

public class EntityEvent implements Event{

    private Collection<?> entities;
    @Override
    public EventAction getAction() {
        return null;
    }

    @Override
    public void setAction(EventAction action) {

    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> Collection<T> getEntities() {
        return (Collection<T>) this.entities;
    }


    @Override
    public <T> void setEntities(Collection<T> entities) {
        this.entities = entities;
    }
}
