package sk.durovic.result;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collection;
import java.util.Map;

public interface Result {
    @JsonIgnore
    boolean isOk();
    void setEntities(Collection<?> entities);
    <T> Collection<T> getEntities();
    public void addMessage(String entityId, String message);
    public String getMessage(String entityId);
    public Map<String, String> getMessages();
    void setState(ResultState state);
}
