package sk.durovic.jms.messaging.worker;

import sk.durovic.jms.messaging.event.Event;
import sk.durovic.jms.messaging.worker.result.WorkerResult;

import java.io.Serializable;

public interface JmsMessageWorker<T extends Serializable> {

     WorkerResult<T> processEvent(Event<T> event);

}
