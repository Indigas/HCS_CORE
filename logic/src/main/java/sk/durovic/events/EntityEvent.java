package sk.durovic.events;

import lombok.Builder;
import sk.durovic.dto.PatientDto;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
