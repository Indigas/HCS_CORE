package sk.durovic.jms.messaging.actions;

import java.io.Serializable;

public enum JmsEntityAction implements Serializable {
    GET,
    CREATE,
    UPDATE,
    DELETE;
}
