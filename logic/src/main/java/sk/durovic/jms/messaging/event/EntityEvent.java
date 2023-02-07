package sk.durovic.jms.messaging.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import sk.durovic.jms.messaging.actions.JmsAction;
import sk.durovic.jms.messaging.actions.JmsEntityAction;
import sk.durovic.jms.messaging.event.result.EventMessageResult;
import sk.durovic.jms.messaging.event.result.EventStatusResult;
import sk.durovic.jms.messaging.exception.OperationNotSupported;

import java.io.Serializable;

public abstract class EntityEvent<T> implements Serializable, Event<T> {

    protected T entity;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    protected JmsEntityAction action;
    protected EventMessageResult result;

    @Override
    public T getEntity() {
        return entity;
    }

    @Override
    public void setEntity(T entity){
        this.entity = entity;
    }


    @Override
    public JmsAction getAction() {
        return action;
    }

    @Override
    @JsonTypeInfo(use= JsonTypeInfo.Id.CLASS, defaultImpl = sk.durovic.jms.messaging.actions.JmsEntityAction.class)
    @JsonSubTypes({
            @JsonSubTypes.Type(value=sk.durovic.jms.messaging.actions.JmsEntityAction.class, name="JmsEntityAction")})
    public void setAction(JmsAction action) {
        this.action = (JmsEntityAction) action;
    }

    @Override
    public EventMessageResult getResult() {
        return result;
    }

    @Override
    public void setResult(EventMessageResult result) {
        this.result = result;
    }

    @Override
    @JsonIgnore
    public boolean isResultOk(){
        return getResult().getStatus() == EventStatusResult.OK;
    }

    public static <R> Event<R> createDefaultEvent(){
        return new DefaultEvent<>();
    }

    public static class DefaultEvent<R> extends EntityEvent<R> {
        private DefaultEvent() {
        }

        @Override
        public R getEntity() {
            throw new OperationNotSupported("Not supported on default event!");
        }

        @Override
        public void setEntity(R entity) {
            throw new OperationNotSupported("Not supported on default event!");
        }

        @Override
        public JmsEntityAction getAction() {
            throw new OperationNotSupported("Not supported on default event!");
        }

        @Override
        public void setAction(JmsAction action) {
            throw new OperationNotSupported("Not supported on default event!");
        }

    }
}
