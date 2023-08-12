package sk.durovic.result;

import io.swagger.annotations.ApiModelProperty;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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

    @SuppressWarnings("unchecked")
    @Override
    public <T> Collection<T> getEntities() {
        return (Collection<T>) this.entities;
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
