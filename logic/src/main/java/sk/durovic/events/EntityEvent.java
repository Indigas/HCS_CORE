package sk.durovic.events;

import java.util.Collection;

public class EntityEvent implements Event{

    public EntityEvent() {
    }

    private Collection<?> entities;
    private EventAction action;

    @Override
    public EventAction getAction() {
        return action;
    }

    @Override
    public void setAction(EventAction action) {
        this.action = action;
    }

    @Override
    public Collection<?> getEntities() {
        return this.entities;
    }

    @Override
    public void setEntities(Collection<?> entities) {
        this.entities = entities;
    }


}
