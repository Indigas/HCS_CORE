package sk.durovic.jms.events;

import org.springframework.jms.core.JmsTemplate;
import sk.durovic.jms.events.worker.JmsMessageProcessor;
import sk.durovic.jms.messaging.worker.JmsMessageWorker;
import sk.durovic.worker.JmsWorkerService;

import javax.jms.Message;

public abstract class EntityListener<T> {

    private final JmsTemplate jmsTemplate;
    private final JmsMessageProcessor<T> messageProcessor;

    protected EntityListener(JmsTemplate jmsTemplate, JmsMessageWorker<T> worker) {
        this.jmsTemplate = jmsTemplate;
        this.messageProcessor = createJmsProcessMessage(worker);
    }

    private JmsMessageProcessor<T> createJmsProcessMessage(JmsMessageWorker<T> worker){
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
    public abstract void receiveAndReplyMessage(Message msg);
}
