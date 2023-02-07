package sk.durovic.jms.messaging.actions;

import com.fasterxml.jackson.annotation.JsonTypeName;

import java.io.Serializable;

@JsonTypeName("JmsEntityAction")
public enum JmsEntityAction implements Serializable, JmsAction {
    GET,
    CREATE,
    UPDATE,
    DELETE,
    GET_ALL;
}
