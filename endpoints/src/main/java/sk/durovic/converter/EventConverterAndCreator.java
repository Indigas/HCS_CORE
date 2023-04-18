package sk.durovic.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import sk.durovic.dto.EntityDto;
import sk.durovic.dto.PatientDto;
import sk.durovic.events.EntityEvent;
import sk.durovic.events.Event;
import sk.durovic.events.EventAction;
import sk.durovic.helper.EntityMapperHelper;
import sk.durovic.mapper.EntityConverter;

import javax.jms.JMSException;
import javax.jms.Message;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class EventConverterAndCreator {

    private EventConverterAndCreator() {
    }

    public static <T> Event convertJms2Event(Message msg, Class<T> clazz) throws JMSException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonMsg = objectMapper.readTree(msg.getBody(String.class));
        CollectionType type = objectMapper.getTypeFactory().constructCollectionType(List.class, clazz);

        List<T> entities = objectMapper.readValue(jsonMsg.get("entities").toString(), type);
        EventAction action = objectMapper.readValue(jsonMsg.get("action").toString(), EventAction.class);


        Event event = new EntityEvent();

        EntityConverter<T, ?> converter = EntityMapperHelper.getConverter(clazz);
        event.setEntities(entities.stream().map(converter::convert2Entity).collect(Collectors.toList()));
        event.setAction(action);

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
