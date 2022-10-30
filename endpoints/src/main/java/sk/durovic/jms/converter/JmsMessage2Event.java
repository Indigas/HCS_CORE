package sk.durovic.jms.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import sk.durovic.jms.messaging.event.Event;
import sk.durovic.jms.messaging.event.PatientEvent;
import sk.durovic.model.BaseEntityAbstractClass;
import sk.durovic.worker.JmsWorkerTask;

import javax.jms.JMSException;
import javax.jms.Message;
import java.io.Serializable;
import java.util.Optional;

@Slf4j
public class JmsMessage2Event {

    public static <T extends BaseEntityAbstractClass<ID>, ID extends Serializable, R extends Event<T>>
                Optional<Event<T>> convertMsg2Event(Message message, Class<R> clazz){
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            Event<T> event = objectMapper.readValue(message.getBody(String.class), clazz);
            return Optional.of(event);
        } catch (JMSException | JsonProcessingException e){
            log.error("JMS message error", e);
        }

        return Optional.empty();
    }
}
