package sk.durovic.result;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EntityResult implements Result{

    private Collection<?> entities;
    private final List<String> messages = new ArrayList<>();
    private ResultState state;

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
    public void addMessage(String message) {
        this.messages.add(message);
    }

    @Override
    public List<String> getMessages() {
        return messages;
    }

    @Override
    public void setState(ResultState state) {
        this.state = state;
    }


}
