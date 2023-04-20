package sk.durovic.jms.listeners;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import sk.durovic.converter.EventConverterAndCreator;
import sk.durovic.events.Event;
import sk.durovic.processor.RequestProcessor;
import sk.durovic.result.Result;

import javax.jms.JMSException;
import javax.jms.Message;

@Slf4j
public abstract class EntityListener {

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

    protected void processMessage(Message msg, Class<?> clazz){

        try {
            Event event = EventConverterAndCreator.convertJms2Event(msg, clazz);

            Result result = messageProcessor.process(event);

            if(!result.isOk()){
                log.error("Error was returned by message processor");
            }

            if (msg.getJMSReplyTo() != null) {
                jmsTemplate.convertAndSend(msg.getJMSReplyTo(), result);
            }
        } catch (JMSException e){
            log.error("JMS exception", e);
        } catch (JsonProcessingException e) {
            log.error("Converting from json failed." , e);
        }
    }

}
