package sk.durovic.result;

import java.util.Collection;
import java.util.List;

public interface Result {
    boolean isOk();
    void setEntities(Collection<?> entities);
    Collection<?> getEntities();
    void addMessage(String message);
    List<String> getMessages();
    void setState(ResultState state);
}
