package sk.durovic.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import sk.durovic.events.EntityEvent;
import sk.durovic.events.Event;
import sk.durovic.helper.EntityMapperHelper;
import sk.durovic.mapper.EntityConverter;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.List;
import java.util.stream.Collectors;

public class EventConverter {

    private EventConverter() {
    }

    public static <T> Event convertJms2Event(Message msg, Class<?> clazz) throws JMSException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Event event = new EntityEvent();
        List<T> entities = objectMapper.readValue(msg.getBody(String.class), new TypeReference<>() {
            });

        EntityConverter<T, ?> converter = EntityMapperHelper.getConverter(clazz);
        event.setEntities(entities.stream().map(converter::convert2Entity).collect(Collectors.toList()));

        return event;
    }
}
