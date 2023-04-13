package sk.durovic.result;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EntityResult<T> implements Result<T>{

    private Collection<T> entities;
    private final List<String> messages = new ArrayList<>();

    @Override
    public boolean isOk() {
        return false;
    }

    @Override
    public void setEntities(Collection<T> entities) {
        this.entities = entities;
    }

    @Override
    public Collection<T> getEntities() {
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


}
