package sk.durovic.jms.messaging.actions;

import java.io.Serializable;

public enum JmsEntityAction implements Serializable, JmsAction {
    GET,
    CREATE,
    UPDATE,
    DELETE;
}
