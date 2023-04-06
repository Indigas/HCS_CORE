package sk.durovic.jms.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import sk.durovic.jms.events.processor.JmsMessageProcessor;

import javax.jms.Message;

@Slf4j
public abstract class EntityListener<T> {

    private final JmsTemplate jmsTemplate;
    private final JmsMessageProcessor<T> messageProcessor;

    protected EntityListener(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
        this.messageProcessor = createJmsMessageProcessor();
    }

    protected JmsMessageProcessor<T> createJmsMessageProcessor(){
        JmsMessageProcessor<T> jmsMessageProcessor = new JmsMessageProcessor<>();
        jmsMessageProcessor.setJmsTemplate(jmsTemplate);

        return jmsMessageProcessor;
    }

    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    public JmsMessageProcessor<T> getMessageProcessor() {
        return messageProcessor;
    }

    public abstract void receiveMessage(Message msg);

    protected void processMessage(Message msg){
    }

}
