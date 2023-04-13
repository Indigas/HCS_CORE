package sk.durovic.result;

import java.util.Collection;
import java.util.List;

public interface Result<T> {
    boolean isOk();
    void setEntities(Collection<T> entities);
    Collection<T> getEntities();
    void addMessage(String message);
    List<String> getMessages();
}
