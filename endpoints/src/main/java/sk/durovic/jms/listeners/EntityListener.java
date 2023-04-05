package sk.durovic.jms.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import sk.durovic.jms.messaging.event.EntityEvent;
import sk.durovic.jms.messaging.worker.JmsMessageWorker;
import sk.durovic.jms.processor.JmsMessageProcessor;
import sk.durovic.worker.JmsWorkerExecutorService;

import javax.jms.Message;
import java.io.Serializable;

@Slf4j
public abstract class EntityListener<T extends Serializable, R, ID> {

    private final JmsTemplate jmsTemplate;
    private final JmsMessageProcessor<T, R, ID> messageProcessor;

    protected EntityListener(JmsTemplate jmsTemplate, JmsMessageWorker<R, ID> worker) {
        this.jmsTemplate = jmsTemplate;
        this.messageProcessor = createJmsMessageProcessor(worker);
    }

    protected JmsMessageProcessor<T, R, ID> createJmsMessageProcessor(JmsMessageWorker<R, ID> worker){
        JmsMessageProcessor<T, R, ID> jmsMessageProcessor = new JmsMessageProcessor<>();
        jmsMessageProcessor.setWorker(worker);
        jmsMessageProcessor.setJmsTemplate(jmsTemplate);
        jmsMessageProcessor.setWorkerService(new JmsWorkerExecutorService());

        return jmsMessageProcessor;
    }

    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    public JmsMessageProcessor<T, R, ID> getMessageProcessor() {
        return messageProcessor;
    }

    public abstract void receiveMessage(Message msg);

    protected void processMessage(Message msg, Class<? extends EntityEvent<T>> clazz){
        messageProcessor.processMessage(msg, clazz);
    }

}
