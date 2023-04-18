package sk.durovic.events;

import lombok.Builder;
import sk.durovic.dto.PatientDto;

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

    @SuppressWarnings("unchecked")
    @Override
    public <T> Collection<T> getEntities() {
        return (Collection<T>) entities;
    }

    @Override
    public <T> void setEntities(Collection<T> entities) {
        this.entities = entities;
    }


}
