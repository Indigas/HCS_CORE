package sk.durovic.jms.messaging.event.result;

import java.io.Serializable;

public enum EventStatusResult implements Serializable {
    OK,
    ERROR,
    UNKNOWN,
    SERVER_ERROR,
    WRONG_INPUT_DATA,
    BAD_REQUEST;
}
