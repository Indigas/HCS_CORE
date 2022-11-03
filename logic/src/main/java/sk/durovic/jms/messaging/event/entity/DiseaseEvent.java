package sk.durovic.jms.messaging.event.entity;

import sk.durovic.jms.messaging.event.EntityEvent;
import sk.durovic.model.Disease;

public class DiseaseEvent extends EntityEvent<Disease> {

    private void setDisease(Disease disease){
        this.entity = disease;
    }
}
