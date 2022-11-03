package sk.durovic.jms.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import sk.durovic.jms.messaging.event.EntityEvent;
import sk.durovic.jms.messaging.event.Event;
import sk.durovic.jms.messaging.event.result.EventMessageResult;
import sk.durovic.jms.messaging.event.result.EventStatusResult;

import javax.jms.JMSException;
import javax.jms.Message;

@Slf4j
public class JmsMessage2Event {

    public static <T, R extends Event<T>>
    Event<T> convertMsg2Event(Message message, Class<R> eventClazz){
        ObjectMapper objectMapper = new ObjectMapper();
        Event<T> event = EntityEvent.createDefaultEvent();;
        EventMessageResult result = new EventMessageResult();

        try {
            event = objectMapper.readValue(message.getBody(String.class), eventClazz);

            result.setStatus(EventStatusResult.OK);
            event.setResult(result);

        } catch (JMSException | JsonProcessingException e){
            log.error("JMS message error", e);
            result.setStatus(EventStatusResult.ERROR);
            result.setMessage("Unexpected error when converting message");
        }

        return event;
    }
}
