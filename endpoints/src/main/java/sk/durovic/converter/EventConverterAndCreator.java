package sk.durovic.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import sk.durovic.events.EntityEvent;
import sk.durovic.events.Event;
import sk.durovic.events.EventAction;
import sk.durovic.helper.EntityMapperHelper;
import sk.durovic.mapper.EntityConverter;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.Collection;
import java.util.stream.Collectors;

public class EventConverterAndCreator {

    private EventConverterAndCreator() {
    }

    public static <T> Event convertJms2Event(Message msg, Class<?> clazz) throws JMSException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Event eventMsg = objectMapper.readValue(msg.getBody(String.class), new TypeReference<>() {
            });

        Event event = new EntityEvent();
        Collection<T> entities = eventMsg.getEntities();

        EntityConverter<T, ?> converter = EntityMapperHelper.getConverter(clazz);
        event.setEntities(entities.stream().map(converter::convert2Entity).collect(Collectors.toList()));
        event.setAction(eventMsg.getAction());

        return event;
    }

    public static <T> Event createRestEvent(Collection<T> objects, EventAction action){
        Event event = new EntityEvent();

        EntityConverter<T,?> converter = EntityMapperHelper.getConverter(objects.iterator().next().getClass());
        event.setAction(action);
        event.setEntities(objects.stream().map(converter::convert2Entity).collect(Collectors.toList()));

        return event;
    }
}
