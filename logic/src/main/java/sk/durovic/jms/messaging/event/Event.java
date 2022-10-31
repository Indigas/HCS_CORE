package sk.durovic.jms.messaging.event;

import sk.durovic.jms.messaging.actions.JmsEntityAction;
import sk.durovic.jms.messaging.event.result.EventMessageResult;
import sk.durovic.jms.messaging.event.result.EventStatusResult;

import java.io.Serializable;

public abstract class Event<T> implements Serializable {

    protected T entity;
    protected JmsEntityAction action;
    protected EventMessageResult result;

    public T getEntity() {
        return entity;
    }

    public JmsEntityAction getAction() {
        return action;
    }

    protected void setAction(JmsEntityAction action) {
        this.action = action;
    }

    public EventMessageResult getResult() {
        return result;
    }

    public void setResult(EventMessageResult result) {
        this.result = result;
    }

    public boolean isResultOk(){
        return getResult().getStatus() == EventStatusResult.OK;
    }

    public static <R> DefaultEvent<R> createDefaultEvent(){
        return new DefaultEvent<>();
    }

    public static class DefaultEvent<R> extends Event<R> {
        private DefaultEvent() {
        }

    }
}
