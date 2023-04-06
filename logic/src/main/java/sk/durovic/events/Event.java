package sk.durovic.events;

public interface Event {
    EventAction getAction();
    void setAction(EventAction action);
}
