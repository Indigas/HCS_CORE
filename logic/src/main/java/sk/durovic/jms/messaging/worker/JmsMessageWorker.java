package sk.durovic.jms.messaging.worker;

import sk.durovic.jms.messaging.event.Event;
import sk.durovic.jms.messaging.worker.result.WorkerResult;

public interface JmsMessageWorker<T> {

     void processMessage(Event<T> message);
     WorkerResult<T> processMessageWithReply(Event<T> message);

}
