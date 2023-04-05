package sk.durovic.jms.messaging.worker;

import sk.durovic.jms.messaging.actions.JmsAction;

public interface JmsMessageWorker<T, ID> {

     //WorkerResult<T> processEvent(Event<T> event);
     void processActionOnEntity(T entity, JmsAction action);

}
