package sk.durovic.jms.events.entity;

import sk.durovic.dto.DiseaseDto;
import sk.durovic.jms.messaging.event.EntityEvent;
import sk.durovic.model.Disease;

public class DiseaseEvent extends EntityEvent<DiseaseDto> {

    private void setDisease(DiseaseDto disease){
        this.entity = disease;
    }
}
