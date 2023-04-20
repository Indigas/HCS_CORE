package sk.durovic.result;

import java.util.*;

public class EntityResult implements Result{

    private Collection<?> entities;
    private final Map<String, String> messages = new HashMap<>();
    private ResultState state = ResultState.ERROR;

    @Override
    public boolean isOk() {
        return state == ResultState.OK;
    }

    @Override
    public void setEntities(Collection<?> entities) {
        this.entities = entities;
    }

    @Override
    public Collection<?> getEntities() {
        return this.entities;
    }


    @Override
    public void addMessage(String entityId, String message) {
        this.messages.put(entityId, message);
    }

    @Override
    public Map<String, String> getMessages() {
        return this.messages;
    }

    @Override
    public String getMessage(String entityId) {
        return messages.get(entityId);
    }

    @Override
    public void setState(ResultState state) {
        this.state = state;
    }


}
