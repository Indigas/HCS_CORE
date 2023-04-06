package sk.durovic.jms.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import sk.durovic.processor.RequestProcessor;

import javax.jms.Message;

@Slf4j
public abstract class EntityListener<T> {

    private final JmsTemplate jmsTemplate;
    private final RequestProcessor messageProcessor;

    protected EntityListener(JmsTemplate jmsTemplate, RequestProcessor messageProcessor) {
        this.jmsTemplate = jmsTemplate;
        this.messageProcessor = messageProcessor;
    }

    protected JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    protected RequestProcessor getMessageProcessor() {
        return messageProcessor;
    }

    public abstract void receiveMessage(Message msg);

    protected void processMessage(Message msg){
    }

}
