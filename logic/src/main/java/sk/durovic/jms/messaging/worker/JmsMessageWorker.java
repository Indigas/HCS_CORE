package sk.durovic.jms.messaging.worker;

public interface JmsMessageWorker {

     void processMessage(Object message);
     Object processMessageWithReply(Object message);

}
