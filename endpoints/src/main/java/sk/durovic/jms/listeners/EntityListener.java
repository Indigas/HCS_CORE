package sk.durovic.jms.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import sk.durovic.converter.EventConverter;
import sk.durovic.events.Event;
import sk.durovic.processor.RequestProcessor;
import sk.durovic.result.Result;

import javax.jms.JMSException;
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
        Event event = EventConverter.convertJms2Event(msg);

        Result<?> result = messageProcessor.process(event);

        try {
            if (result.isOk() && msg.getJMSReplyTo() != null) {
                jmsTemplate.convertAndSend(msg.getJMSReplyTo(), result);
            }
        } catch (JMSException e){
            log.error("JMS exception", e);
        }
    }

}
