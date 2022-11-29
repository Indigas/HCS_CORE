package sk.durovic.jms.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import sk.durovic.jms.converter.JmsMessage2Event;
import sk.durovic.jms.events.worker.JmsMessageProcessor;
import sk.durovic.jms.messaging.event.EntityEvent;
import sk.durovic.jms.messaging.event.Event;
import sk.durovic.jms.messaging.worker.JmsMessageWorker;
import sk.durovic.jms.messaging.worker.result.WorkerResult;
import sk.durovic.worker.JmsWorkerService;

import javax.jms.JMSException;
import javax.jms.Message;

@Slf4j
public abstract class EntityListener<T> {

    private final JmsTemplate jmsTemplate;
    private final JmsMessageProcessor<T> messageProcessor;

    protected EntityListener(JmsTemplate jmsTemplate, JmsMessageWorker worker) {
        this.jmsTemplate = jmsTemplate;
        this.messageProcessor = createJmsProcessMessage(worker);
    }

    private JmsMessageProcessor<T> createJmsProcessMessage(JmsMessageWorker worker){
        JmsMessageProcessor<T> jmsMessageProcessor = new JmsMessageProcessor<>();
        jmsMessageProcessor.setWorker(worker);
        jmsMessageProcessor.setJmsTemplate(jmsTemplate);
        jmsMessageProcessor.setWorkerService(new JmsWorkerService());

        return jmsMessageProcessor;
    }

    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    public JmsMessageProcessor<T> getMessageProcessor() {
        return messageProcessor;
    }

    public abstract void receiveMessage(Message msg);

    protected void defaultMessageProcessing(Message msg, Class<? extends EntityEvent<T>> clazz){
        Event<T> event = JmsMessage2Event.convertMsg2Event(msg, clazz);
        WorkerResult<?> result = messageProcessor.processMessage(event);

        try {
            if (event.isResultOk())
                getJmsTemplate().convertAndSend(msg.getJMSReplyTo(), result);

        } catch (JMSException e){
            log.error("JMS exception", e);
        }
    }

}
