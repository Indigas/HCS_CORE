package sk.durovic.jms.messaging.worker;

import sk.durovic.jms.messaging.event.Event;
import sk.durovic.jms.messaging.worker.result.WorkerResult;

public interface JmsMessageWorker {

     WorkerResult<?> processEvent(Event<?> event);

}
