package sk.durovic.jms.events;

import javax.jms.Message;

public interface EntityListener {
    public void receiveMessage(Message msg);
}
