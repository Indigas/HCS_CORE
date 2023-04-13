package sk.durovic.converter;

import sk.durovic.events.Event;

import javax.jms.Message;

public class EventConverter {

    private EventConverter() {
    }

    public static Event convertJms2Event(Message msg){
        return null;
    }
}
