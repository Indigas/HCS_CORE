package sk.durovic.jms.messaging.event.entity;

import sk.durovic.jms.messaging.event.Event;
import sk.durovic.model.Disease;

public class DiseaseEvent extends Event<Disease> {

    private void setDisease(Disease disease){
        this.entity = disease;
    }
}
