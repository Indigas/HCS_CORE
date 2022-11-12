package sk.durovic.jms.messaging.event;

import sk.durovic.jms.messaging.actions.JmsAction;
import sk.durovic.jms.messaging.event.result.EventMessageResult;

public interface Event<T> {

    void setEntity(T entity);
    T getEntity();
    void setResult(EventMessageResult result);
    EventMessageResult getResult();
    boolean isResultOk();
    JmsAction getAction();
    void setAction(JmsAction action);
}
