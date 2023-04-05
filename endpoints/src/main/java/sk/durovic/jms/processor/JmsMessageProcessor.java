package sk.durovic.jms.processor;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import sk.durovic.jms.converter.JmsMessage2Event;
import sk.durovic.jms.messaging.event.EntityEvent;
import sk.durovic.jms.messaging.event.Event;
import sk.durovic.jms.messaging.worker.JmsMessageWorker;
import sk.durovic.jms.messaging.worker.result.EntityWorkerResult;
import sk.durovic.jms.messaging.worker.result.WorkerResult;
import sk.durovic.worker.JmsWorkerExecutorService;

import javax.jms.JMSException;
import javax.jms.Message;
import java.io.Serializable;

@Slf4j
@Setter
public class JmsMessageProcessor<T extends Serializable, R, ID> {

    private JmsTemplate jmsTemplate;
    private JmsWorkerExecutorService workerService;
    private JmsMessageWorker<R, ID> worker;

    public JmsMessageProcessor() {
    }

    public void processMessage(Message msg, Class<? extends EntityEvent<T>> clazz){
        Event<T> event = JmsMessage2Event.convertMsg2Event(msg, clazz);
        event.getAction();
        event.getEntity();
        //convert entity dto to entity
        // worker.processActionOnEntity(entity, action);
        WorkerResult<T> result = new EntityWorkerResult<>();

        try {
            if (event.isResultOk() && msg.getJMSReplyTo() != null)
                jmsTemplate.convertAndSend(msg.getJMSReplyTo(), result);

        } catch (JMSException e){
            log.error("JMS exception", e);
        }

    }

}
