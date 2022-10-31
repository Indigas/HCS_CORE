package sk.durovic.jms.messaging.event.result;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class EventMessageResult implements Serializable {

    private String message;
    private EventStatusResult status;

    public EventMessageResult() {
        this.message = "";
    }

}
