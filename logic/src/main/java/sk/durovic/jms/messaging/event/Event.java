package sk.durovic.jms.messaging.event;

import sk.durovic.jms.messaging.actions.JmsAction;
import sk.durovic.jms.messaging.event.result.EventMessageResult;

import java.io.Serializable;

public interface Event<T extends Serializable> {

    void setEntity(T entity);
    T getEntity();
    void setResult(EventMessageResult result);
    EventMessageResult getResult();
    boolean isResultOk();
    JmsAction getAction();
    void setAction(JmsAction action);
}
