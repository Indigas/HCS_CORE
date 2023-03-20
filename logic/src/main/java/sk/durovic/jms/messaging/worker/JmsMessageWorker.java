package sk.durovic.jms.messaging.worker;

import sk.durovic.jms.messaging.event.Event;
import sk.durovic.jms.messaging.worker.result.WorkerResult;

import java.io.Serializable;

public interface JmsMessageWorker<R extends Serializable> {

     WorkerResult<R> processEvent(Event<R> event);

}
